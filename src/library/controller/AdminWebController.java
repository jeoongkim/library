package library.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import library.model.BookException;
import library.model.FileException;
import library.model.HistoryException;
import library.model.LoanException;
import library.model.ReservationException;
import library.model.UsersException;
import library.model.vo.AudioList;
import library.model.vo.Authority;
import library.model.vo.AuthorityId;
import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.BookList;
import library.model.vo.History;
import library.model.vo.Loan;
import library.model.vo.Reservation;
import library.model.vo.Users;
import library.service.BookListService;
import library.service.BookService;
import library.service.FileService;
import library.service.HistoryService;
import library.service.LoanService;
import library.service.MailService;
import library.service.ReservationService;
import library.service.UsersService;

@Controller
@RequestMapping("/admin")
public class AdminWebController 
{
	@Autowired
	private UsersService usersService;

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private FileService fileService;
	
	
	@Autowired
	private MailService mailService;

	private Users user;

	private Book book;
	
	@RequestMapping(value="admin-index.do", method=RequestMethod.GET)
	public String index(Model model) 
	{
		List<Reservation> reservation=null;
		List<Loan> loan=null;
		try {
			reservation = reservationService.selectJoinList();
			loan = loanService.selectJoinList();
			
			model.addAttribute("reservation",reservation);
			model.addAttribute("loan",loan);
			
		} catch (ReservationException e) {
			System.out.println(e.getMessage());
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		}
		
		return "/admin/admin-index";
	}
	
	// 대출
	@RequestMapping(value="loan-add.do",method=RequestMethod.GET)
	public String add(HttpServletRequest request) 
	{
		Loan loan=new Loan();
		Date loanDate = new Date();
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(loanDate);
		deadline.add(Calendar.DATE, 14);
		
		loan.setLoanDate(loanDate);
		loan.setDeadline(deadline.getTime());
		
		request.setAttribute("loan", loan);
		
		
		return "/admin/loan-add";
	}
	@RequestMapping(value="userNoCheck.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<Object, Integer> userNoCheck(int userNo)
	{
		System.out.println("userNoCheck.do 전달받은  userNo"+userNo);
		Map<Object, Integer> map =null;
		user=null;
		try {
			user = usersService.detail(userNo);
			System.out.println("detail에서 받은 userNo "+user.getUserNo());
			map.put("user", user.getUserNo());
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="loan-add.do",method=RequestMethod.POST)
	public String loanAdd(HttpServletRequest request, Integer userNo,Integer bookNo) 
	{
		Loan loan=new Loan();
		History history = new History();
		List<Loan> loanList = null;
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(new Date());
		deadline.add(Calendar.DATE, 14);

		try {
			Users user = usersService.detail(userNo);
			System.out.println(user.getAuthorities());
			if (user.getStopDate() != null) {
				request.setAttribute("users", user);
				return "/admin/loan-BREAKUSERS-false";
			}
			loanList = loanService.selectUserNo(userNo);
			
			loan.setUserNo(userNo);
			loan.setBookNo(bookNo);
			loan.setDeadline(deadline.getTime());
			
			history.setBookNo(bookNo);
			history.setUserNo(userNo);
			
			BookList bookList = bookListService.detail(bookNo);
			
			if(bookList.isWhether() && loanList.size() < 5) {
				loanService.loanBook(loan);
				historyService.insertHistory(history);
				bookListService.loanBookList(bookNo);
				
			}
			else {
				return "/admin/loan-false";
			}
		}  catch (LoanException e) {
			request.setAttribute("error", "server");
		} catch (BookException e) {
			request.setAttribute("error", "server");
		} catch (HistoryException e) {
			request.setAttribute("error", "server");
		} catch (UsersException e) {
			request.setAttribute("error", "server");
		}

		return "/admin/loan-confirm";
	}
	
	@RequestMapping(value="re-loan-add.do",method=RequestMethod.GET)
	public String readd(HttpServletRequest request) 
	{
		Loan loan=new Loan();
		Date loanDate = new Date();
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(loanDate);
		deadline.add(Calendar.DATE, 14);
		
		loan.setLoanDate(loanDate);
		loan.setDeadline(deadline.getTime());
		
		request.setAttribute("loan", loan);
		
		return "/admin/re-loan-add";
	}

	// 예약한 책 대출
	@RequestMapping(value="re-loan-add.do",method=RequestMethod.POST)
	public String reloanAdd(HttpServletRequest request, Integer userNo,Integer bookNo) 
	{
		History history = new History();
		List<Loan> loanList = null;
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(new Date());
		deadline.add(Calendar.DATE, 14);

		try {
			loanList = loanService.selectUserNo(userNo);
			
			history.setBookNo(bookNo);
			history.setUserNo(userNo);
	
			Loan loanNum = loanService.loanNum(bookNo);
			
			if (loanList.size() < 5) {
				loanService.updateReservLoan(loanNum.getLoanNo());
				historyService.insertHistory(history);
			}
			else {
				return "/admin/loan-false";
			}

		}  catch (LoanException e) {
			System.out.println(e.getMessage());
		} catch (HistoryException e) {
			System.out.println(e.getMessage());
		}
		return "/admin/loan-confirm";
	}
	
	// 반납
	@RequestMapping(value="loan-delete.do",method=RequestMethod.GET)
	public String loanDelete() 
	{
		return"/admin/loan-delete";
	}
	@RequestMapping(value="loan-delete.do",method=RequestMethod.POST)
	public String loanDelete(Model model, Integer bookNo) 
	{
		Loan loan=new Loan();
		Loan loanUser = new Loan();
		History history = new History();
		Users users = new Users();
		Calendar deadline = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		deadline.setTime(new Date());
		deadline.add(Calendar.DATE, 4);
		now.setTime(new Date());
		now.add(Calendar.DATE, -1);
		try {
			BookList bookList = bookListService.detail(bookNo);
			if (bookList == null) {
				return "redirect:loan-delete.do?error=booknumber";
			}
			Reservation reservation = reservationService.isbnReservation(bookList.getIsbn());
			loanUser = loanService.loanNum(bookNo);
			
			if (loanUser == null) {
				return "redirect:loan-delete.do?error=number";
			}
			loanService.returnBook(loanUser.getLoanNo());
			users = usersService.detail(loanUser.getUserNo());
			history.setBookNo(bookNo);
			history.setLoanDate(loanUser.getLoanDate());
			history.setUserNo(loanUser.getUserNo());

			if (!(reservation==null)) 
			{
				loan.setUserNo(reservation.getUserNo());
				loan.setBookNo(bookNo);
				loan.setDeadline(deadline.getTime());
				
				Integer userNo = reservation.getUserNo();
				Users user = usersService.detail(userNo);
				user.setPassword(null);
				String isbn = bookList.getIsbn();
				Book book = bookService.detail(isbn);
				mailService.nextLoanResrv(user, book, loanUser);
				
				loanService.insertReservLoan(loan);
				reservationService.deleteReservation(reservation.getReserNo());
			}
			else
			{
				bookListService.returnBookList(bookNo);
			}
			
				historyService.updateHistory(history);
				
				model.addAttribute("loan", loanUser);
				model.addAttribute("users", users);
				System.out.println(loanUser.getDeadline());
				if (now.getTime().after(loanUser.getDeadline())) {
					
					return "/admin/loan-return-confirm2";
				}
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} catch (BookException e) {
			System.out.println(e.getMessage());
		} catch (HistoryException e) {
			System.out.println(e.getMessage());
		} catch (ReservationException e) {
			System.out.println(e.getMessage());
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
		
		return "/admin/loan-return-confirm";
	}
	
	// 책 추가
	@RequestMapping(value="book-add1.do", method=RequestMethod.GET)
	public String bookAdd1() {
		
		return "admin/book-add1";
	}
	// ISBN 중복확인 및 정보확인
	@RequestMapping(value="isbnCheck.do",method= RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object> isbnCheck(String isbn)
	{
		Map<Object, Object> map = new HashMap<Object,Object>();
		try {
			book= bookService.detail(isbn);
			System.out.println(book.getIsbnNo());
			map.put("book", book);
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="book-add1.do",method=RequestMethod.POST)
	public String bookAdd1(HttpServletRequest request, String isbn,
						String title,String author,String publisher,String groupName,
						@RequestParam("attachment") MultipartFile attachment) 
	{
		Book book=new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setGroupName(groupName);
		
		try {
			String filename=fileService.add(request, attachment);
			book.setAttachment(filename);
			
			bookService.add(book);
			
			if (groupName.equals("O")) {
				return "redirect:/admin/book-add3.do?isbn=" + book.getIsbn();
			}
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			request.setAttribute("error", "file");
		}
		return "redirect:/admin/book-add2.do?isbn=" + book.getIsbn();
	}
	
	@RequestMapping(value="book-add2.do", method=RequestMethod.GET)
	public String bookAdd2(Model model, @RequestParam(value = "isbn", required = true) String isbn) {
		
		model.addAttribute("isbn", isbn);
		return "admin/book-add2";
	}
	
	
	@RequestMapping(value="book-add2.do",method=RequestMethod.POST)
	public String bookAdd2(HttpServletRequest request, String publicationDay,
						String wearingDay, String isbn) 
	{
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date wearingDay1;
		try {
			wearingDay1 = transFormat.parse(wearingDay);

			Date publicationDay1 = transFormat.parse(publicationDay);
			BookList bookList = new BookList();
			bookList.setIsbn(isbn);
			bookList.setPublicationDay(publicationDay1);
			bookList.setWearingDay(wearingDay1);

			bookListService.add(bookList);
			System.out.println(bookList);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (ParseException e1) {
			System.out.println(e1.getMessage());
		}
		
		return "redirect:../book-list.do";
	}

	// 책 수정
	@RequestMapping(value="/book-modify1.do", method=RequestMethod.GET)
	public String bookmodify(Integer isbn) {
		
		return "admin/book-modify1";
	}
	// Ajax로 책 수정하기 
	@RequestMapping(value="/book-modify.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> modifyAjax(@RequestBody String isbn) 
	{
		Book book = new Book();
		Map<Object, Object> map =new HashMap<>();
		System.out.println("/book-modify1.do ->전달받은 isbn : "+isbn);
		try {
			book = bookService.detail(isbn);
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		map.put("book", book);
		return map;
	}
	@RequestMapping(value="/book-modify1.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, String isbn) {
		Book book = new Book();
		
		try {
			book = bookService.detail(isbn);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		
		
		request.setAttribute("book", book);
		

		return "redirect:book-modify2.do?isbn=" + isbn;
	}
	@RequestMapping(value="/book-modify2.do", method=RequestMethod.GET)
	public String modify(Model model, String isbn) {
		
		Book book = new Book();
		
		try {
			book = bookService.detail(isbn);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		
		
		model.addAttribute("book", book);
		
		return "admin/book-modify2";
	}
	
	@RequestMapping(value="/book-modify2.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, String isbn, String title, String author, String publisher, String groupName,
			@RequestParam("attachment") MultipartFile attachment) {
		
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setTitle(title);
		book.setGroupName(groupName);
		
		try {
			
			String filename=fileService.add(request, attachment);
			book.setAttachment(filename);
			String toDeleteFilename = bookService.modify(book);
			fileService.remove(request, toDeleteFilename);
			
			if (book.getGroupName().equals("O")) {
				return "redirect:/admin/book-modify3.do?isbn=" + book.getIsbn();
			}
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:../book-list.do";
	}
	
	// 희망도서 신청 목록
	@RequestMapping(value="/book-apply-list.do",method=RequestMethod.GET)
	public String bookApplylist(Model model) {
		List<BookApply> list = null;
		try {
			list = bookService.applyAlllist();
		} catch (BookException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("list", list);
		return "admin/book-apply-list";
	}
	
	// 희망도서 신청 상세보기
	@RequestMapping(value="/book-apply-detail.do", method=RequestMethod.GET)
	public String applyDetail(Model model, HttpServletRequest request, 
			@RequestParam(value="applyNo", required=true) Integer applyNo) {
		BookApply book = new BookApply();
		try {
			book = bookService.applyDetail(applyNo);
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		model.addAttribute("book", book);
		return "admin/book-apply-detail";
	}
	
	// 희망도서신청 입고
	@RequestMapping(value="/book-apply-warehousing.do", method=RequestMethod.POST)
	public String bookWarehousing(Model model, HttpServletRequest request,
			Integer applyNo, Integer userNo, String title, String author, String publisher,
			String isbn, String publicationDay, String wearingDay, String groupName,
			@RequestParam("attachment") MultipartFile attachment) {
		
		BookApply bookapply = new BookApply();
		Book book = new Book();
		BookList list = new BookList();
		try {
			bookapply = bookService.applyDetail(applyNo);
			bookapply.setWarehousing(true);
			bookService.applyupdate(bookapply);
			
			book.setIsbn(isbn);		// book 추가
			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setGroupName(groupName);
			book.setGroupName(groupName);
			String filename=fileService.add(request, attachment);
			book.setAttachment(filename);
			bookService.add(book);
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");	// list 추가
			Date wearingDay1 = transFormat.parse(wearingDay);
			Date publicationDay1 = transFormat.parse(publicationDay);
			list.setIsbn(isbn);
			list.setPublicationDay(publicationDay1);
			list.setWearingDay(wearingDay1);
			bookListService.add(list);
			Integer bookNo = list.getBookNo();
			
			Users user = usersService.detail(userNo);	
			user.setPassword(null);
			mailService.warehousing(user, bookapply);
			
			Integer totalLoan = loanService.totalReserv(userNo);
			Integer totalReserv = reservationService.totalReserv(userNo);
			Integer totalCount = totalLoan + totalReserv;
			if(!(totalCount >= 5)) {
				loanService.insertReserv(userNo, bookNo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		return "admin/book-apply-warehousing";
	}
	
	// 회원이 대출 정보 조회 
	@RequestMapping(value="/searchUserLoanHistory.do", method=RequestMethod.GET)
	public String usersHistory(Model model, String input) 
	{
		Users users = new Users();
		List<Loan> loan = null;
		try {
			if (input != null) {
				loan = loanService.selectSearchUserNo(input);
			}else {
				loan = loanService.selectJoinList();
			}
			
		} catch (LoanException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("loan",loan);
		model.addAttribute("input",input);
		model.addAttribute("users",users);
		
		
		return "admin/loan-user-search";
	}
	
	// 대출 정지
	@RequestMapping(value="/stop-date.do", method=RequestMethod.POST)
	public String stopDate(Model model, Integer userNo, String stopday) 
	{
		Users users = new Users();
		Date stopDate = null;
		try {
			stopDate = new SimpleDateFormat("yyyy-MM-dd").parse(stopday);
			users = usersService.detail(userNo);
			users.setStopDate(stopDate);
			
			usersService.modify(users);
			
			// 변경하려는 사용자의 권한을 입력 (일반 사용자 권한: 30, "BREAKUSERS")
			Authority auth = new Authority(AuthorityId.BREAKUSERS.getAuthorityId(), AuthorityId.BREAKUSERS.name());

			// Set 컬렉션을 이용하여 users 객체에 권한을 담기
			Set<Authority> auths = new HashSet<>();
			auths.add(auth);
			users.setAuthorities(auths);
			users.setUserNo(userNo);
			
			usersService.updateAuthority(users);
			
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} catch (ParseException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("users",users);
		
		
		return "admin/stopDate-confirm";
	}

	// book-add1 에서 도서분류 확인하여 오디오면 add3로 이동
	@RequestMapping(value="book-add3.do", method=RequestMethod.GET)
	public String bookAdd3(Model model, @RequestParam(value = "isbn", required = true) String isbn) {
		model.addAttribute("isbn", isbn);
		return "admin/book-add3";
	}
	
	@RequestMapping(value="book-add3.do", method=RequestMethod.POST)
	public String bookAdd3(Model model, HttpServletRequest request, 
			String publicationDay, String wearingDay, String isbn, 
			@RequestParam("file") MultipartFile file) {
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date wearingDay1;
		Date publicationDay1;
		try {
			wearingDay1 = transFormat.parse(wearingDay);
			publicationDay1 = transFormat.parse(publicationDay);
	
			AudioList audioList = new AudioList();
			audioList.setIsbn(isbn);
			audioList.setPublicationDay(publicationDay1);
			audioList.setWearingDay(wearingDay1);
			
			String filename = fileService.add(request, file);
			audioList.setFile(filename);

			bookListService.insertAudio(audioList);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (ParseException e1) {
			System.out.println(e1.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e2) {
			System.out.println(e2.getMessage());
			request.setAttribute("error", "server");
		}
		return "redirect:../book-audio-list.do";
	}
	
	// 오디오북 삭제
	@RequestMapping(value="/book-audio-remove.do", method=RequestMethod.GET)
	public String audioRemove(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		model.addAttribute("isbn", isbn);
		return "/admin/book-audio-remove-confirm";
	}
	
	@RequestMapping(value="/book-audio-remove.do", method=RequestMethod.POST)
	public String audioRemove(Model model, HttpServletRequest request, String isbn) {
		try {
			String Deletefilename = bookService.remove(isbn);
			fileService.remove(request, Deletefilename);
			bookListService.deleteAudio(isbn);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
	
		return "redirect:../book-audio-list.do";
	}
	
	// 오디오북 수정
	@RequestMapping(value="/book-audio-modify.do", method=RequestMethod.GET)
	public String audioModify(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		model.addAttribute("isbn", isbn);
		return "/admin/book-modify1";
	}

	@RequestMapping(value="book-modify3.do", method=RequestMethod.GET)
	public String bookModify3(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		AudioList audio;
		try {
			audio = bookListService.selectAudio(isbn);
			model.addAttribute("audio", audio);
		} catch (BookException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		
		return "/admin/book-modify3";
	}
	
	@RequestMapping(value="book-modify3.do", method=RequestMethod.POST)
	public String bookModify3(Model model, HttpServletRequest request, 
			String publicationDay, String wearingDay, String isbn, Integer audioNo,
			@RequestParam("file") MultipartFile file) {
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date wearingDay1;
		Date publicationDay1;
		try {
			wearingDay1 = transFormat.parse(wearingDay);
			publicationDay1 = transFormat.parse(publicationDay);
	
			AudioList audioList = new AudioList();
			audioList.setAudioNo(audioNo);
			audioList.setIsbn(isbn);
			audioList.setPublicationDay(publicationDay1);
			audioList.setWearingDay(wearingDay1);
			
			String filename = fileService.add(request, file);
			audioList.setFile(filename);
			String toDeleteFilename = bookListService.updateAudio(audioList);
			fileService.remove(request, toDeleteFilename);
			
		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (ParseException e1) {
			System.out.println(e1.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e2) {
			System.out.println(e2.getMessage());
			request.setAttribute("error", "server");
		}
		return "redirect:../book-audio-list.do";
	}
}






