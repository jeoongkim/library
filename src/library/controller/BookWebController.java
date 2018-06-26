package library.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import library.model.BookException;
import library.model.LoanException;
import library.model.ReservationException;
import library.model.UsersException;
import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.BookList;
import library.model.vo.Loan;
import library.model.vo.Reservation;
import library.model.vo.Users;
import library.service.BookListService;
import library.service.BookService;
import library.service.FileService;
import library.service.LoanService;
import library.service.ReservationService;
import library.service.UsersService;

@Controller
@RequestMapping("/book")
public class BookWebController 
{
	@Autowired
	private BookService bookService;

	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private LoanService loanService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ReservationService reservationService;
	
	// 책 예약
	@RequestMapping(value="reservation-add.do",method=RequestMethod.GET)
	public String add(Model model,Integer isbn) 
	{
		Users users = null;

		try {
			String email = usersService.getPrincipal().getUsername();

			users = usersService.detailByEmail(email);
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		}

		model.addAttribute("users", users);
		model.addAttribute("isbn", isbn);
			
		return "/book/reservation-add";
	}
	@RequestMapping(value="reservation-add.do",method=RequestMethod.POST)
	public String add(HttpServletRequest request, String isbn, Integer userNo) 
	{
		
		Reservation reservation=new Reservation();
		reservation.setIsbn(isbn);
		reservation.setUserNo(userNo);
		
		try {
			int trueCount = bookListService.whetherCount(isbn);		//대출여부 확인 0이면 대출 불가능
			int bookCount = bookListService.count(isbn);			//같은 isbn에 bookList 객수
			int reservationCount = reservationService.ReservationCount(isbn);
			int totalreserv = reservationService.totalReserv(userNo);
			totalreserv += loanService.totalReserv(userNo);
			
			if (trueCount == 0 && bookCount > reservationCount && totalreserv < 5) {
				reservationService.reservationBook(reservation);
				
				return "/book/reservation-confirm";
			}
		} catch (ReservationException e) {
			System.out.println(e.getMessage());
		} catch (BookException e) {
			System.out.println(e.getMessage());
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} 
		return "/book/reservation-false";
	}
	// 책 수정 
	@RequestMapping(value="book-modify.do",method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="isbn",required=true) String isbn) 
	{
		Book book=null;
		try {
			book=bookService.detail(isbn);
		} catch (Exception e) {
			model.addAttribute("erorr","server");
		}
		model.addAttribute("book",book);
		return "book-modify";
	}
	// 희망도서 신청 안내
	@RequestMapping(value="book-apply-info.do", method=RequestMethod.GET)
	public String bookinfo(Model model) {
		List<BookApply> list = null;
		String email = usersService.getPrincipal().getUsername();
		try {
			Users user = usersService.detailByEmail(email);
			Integer userNo = user.getUserNo();
			list = bookService.listByuserNo(userNo);
		} catch (Exception e) {
			model.addAttribute("erorr","server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("list", list);
		return "book/book-apply-info";
	}

	// 희망도서 신청 
	@RequestMapping(value="book-apply.do",method=RequestMethod.GET)
	public String bookApply(Model model) {
		String email = usersService.getPrincipal().getUsername();
		try {
			Users user = usersService.detailByEmail(email);
			user.setPassword(null);
			Integer userNo = user.getUserNo();
			Integer count = bookService.monthCount(userNo);
			if (count > 3) {
				return "/book/book-apply-count";
			}
			model.addAttribute("user", user);
		} catch (Exception e) {
			model.addAttribute("erorr","server");
			System.out.println(e.getMessage());
		}
		return "/book/book-apply";
	}
	
	@RequestMapping(value="book-apply.do",method=RequestMethod.POST)
	public String bookApplyAdd(Model model, HttpServletRequest request,
			String title,
			String author,
			String publisher,
			String isbn,
			Integer userNo) {
		try {
			List<Book> book = null;
			book = bookService.list();
			for (Book b : book) {
				if(b.getIsbn().equals(isbn)) {
					model.addAttribute("book", b);
					return "/book/book-apply-exist";
				}
			}
			BookApply bookapply = new BookApply();
			bookapply.setTitle(title);
			bookapply.setAuthor(author);
			bookapply.setPublisher(publisher);
			bookapply.setUserNo(userNo);
			bookapply.setIsbn(isbn);
			
			bookService.insert(bookapply);
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		return "/book/book-apply-confirm";
	}
	
	@RequestMapping(value="/book-apply-confirm.do",method=RequestMethod.GET)
	public String joinConfirm(Model model) {
		return "/book/book-apply-confirm";
	}
	
	@RequestMapping(value="/book-extension-confirm.do",method=RequestMethod.GET)
	public String bookExtension(Model model) {
		return "/book/book-apply-confirm";
	}
	
	// 예약대출가능 기간 지나면 다음 예약자에게 넘어감
	@Scheduled(cron="0 0 1 * * *")		// 매일 오전 1시 실행
	public void resNext() 
	{
		Loan loan=new Loan();
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(new Date());
		deadline.add(Calendar.DATE, 4);
		System.out.println("실행중...");
		try {
			
			List<Loan> selectDeadlinereserv = loanService.selectDeadlinereserv();
			System.out.println(selectDeadlinereserv);
			
			for (Loan loan1 : selectDeadlinereserv) {
				Integer bookNo = loan1.getBookNo();
				
				BookList bookList = bookListService.detail(bookNo);
				Reservation reservation = reservationService.isbnReservation(bookList.getIsbn());		//예약하나 가져오기
				loanService.returnBook(loan1.getLoanNo());		//론 삭제
				if (!(reservation==null)) 
				{
					loan.setUserNo(reservation.getUserNo());
					loan.setBookNo(bookNo);
					loan.setDeadline(deadline.getTime());
					loanService.insertReservLoan(loan);
					reservationService.deleteReservation(reservation.getReserNo());
				}
				else
				{
					bookListService.returnBookList(bookNo);
				}
				
			}
			
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} catch (BookException e) {
			System.out.println(e.getMessage());
		} catch (ReservationException e) {
			System.out.println(e.getMessage());
		}
	}
}
