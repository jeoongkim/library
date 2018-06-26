package library.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
import library.model.UsersException;
import library.model.vo.Authority;
import library.model.vo.AuthorityId;
import library.model.vo.BookList;
import library.model.vo.History;
import library.model.vo.Loan;
import library.model.vo.Reservation;
import library.model.vo.Users;
import library.service.BookListService;
import library.service.FileService;
import library.service.HistoryService;
import library.service.LoanService;
import library.service.MailService;
import library.service.ReservationService;
import library.service.UsersService;

@Controller
public class UsersWebController 
{
	@Autowired
	private UsersService usersService;
	@Autowired
	private FileService fileService;
	@Autowired
	private BookListService bookListService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private MailService mailService;
	@Autowired
	private ReservationService reservService;

	// 회원목록
	@RequestMapping(value="/admin/users-list.do", method=RequestMethod.GET)
	public String list(Model model, HttpServletRequest req) 
	{
		List<Users> list=null;
		try {
			list=usersService.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error","server");
		}
		model.addAttribute("list",list);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));

		return "admin/users-list";
	}

	/*@RequestMapping("/idcheck.do")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String email) {

		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		count = usersService.idcheck(email);
		map.put("cnt", count);

		return map;
	}*/

	@RequestMapping(value="join.do", method=RequestMethod.GET)
	public String join() 
	{
		return "join";
	}
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(HttpServletRequest request,
			String email,
			String password,
			String name,
			String phoneNum,
			@RequestParam("attachment") MultipartFile attachment) throws UsersException 
	{

		try {
			String filename = fileService.add(request, attachment);
			Users users=new Users(null, email, password, name, phoneNum, filename, null);
			usersService.add(users);
			String encodeName=URLEncoder.encode(users.getName(),"UTF-8");
			users.setPassword(null);
			mailService.sendEmail(users);
			return "redirect:join-confirm.do?name=" + encodeName;

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "encoding");
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:index.do";
	}
	@RequestMapping(value="/join-confirm.do",method=RequestMethod.GET)
	public String joinConfirm(Model model, String name) 
	{
		model.addAttribute("name",name);
		return "join-confirm";
	}
	@RequestMapping(value="/login.do", method=RequestMethod.GET)		//로그인 페이지
	public String login() 
	{
		return "login";
	}

	@RequestMapping(value="/access-denied.do",method=RequestMethod.GET)		// 접근제한
	public String accessDenied(Model model) 
	{
		model.addAttribute("email",usersService.getPrincipal().getUsername());
		return "access-denide";
	}
	@RequestMapping(value="/user/users-modify.do", method=RequestMethod.GET)		//회원정보 수정
	public String modify(HttpServletRequest request) 
	{
		Users users=null;
		String uploadPath=null;

		try {
			String email=usersService.getPrincipal().getUsername();
			users=usersService.detailByEmail(email);
			uploadPath = fileService.getUploadPath(request);

		} catch (Exception e) {
			request.setAttribute("error", "server");
		}
		request.setAttribute("users", users);
		request.setAttribute("uploadPath", uploadPath);

		return "user/users-modify";
	}
	@RequestMapping(value="/user/users-modify.do", method=RequestMethod.POST)	// 비밀번호 변경
	public String modify(HttpServletRequest request,
			String oldPassword,
			String newPassword,
			String name,
			String phoneNum,
			@RequestParam("attachment") MultipartFile attachment) 
	{
		try {
			boolean isMatched = usersService.isPasswordMatched(oldPassword);
			if (isMatched) {
				String email = usersService.getPrincipal().getUsername();
				Users oldUsers = usersService.detailByEmail(email);
				String filename = fileService.add(request, attachment);
				Users users = new Users(oldUsers.getUserNo(), email, newPassword, name, phoneNum, filename, null);
				String toDeleteFile = usersService.modify(users);
				fileService.remove(request, toDeleteFile);
			}
			else 
			{
				return "redirect:/user/users-modify.do?error=password";
			}
		}catch (UsersException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");

		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}

		return "redirect:/user/users-modify-confirm.do";
	}
	@RequestMapping(value="/user/users-modify-confirm.do", method=RequestMethod.GET)
	public String modifyConfirm() 
	{
		return "user/users-modify-confirm";
	}
	@RequestMapping(value="delete-users",method=RequestMethod.POST)
	public String delete(HttpServletRequest request) 
	{
		Users users=null;
		try {
			String email=usersService.getPrincipal().getUsername();
			users=usersService.detailByEmail(email);

		} catch (Exception e) {
			request.setAttribute("error", "server");
		}
		request.setAttribute("users", users);
		return "delete-users";
	}
	// 로그아웃
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		usersService.logout(req, resp);

		return "redirect:/login.do?logout=true";
	}

	@RequestMapping(value="/admin/user-detail.do",method=RequestMethod.GET)
	public String deltail(Model model,@RequestParam(value="userNo",required=true) Integer userNo, HttpServletRequest req)  
	{
		Users users = null;
		List<History> history = null;

		try {
			users = usersService.detail(userNo);
			history = historyService.historyUser(userNo);

		} catch (HistoryException e) {
			System.out.println(e.getMessage());
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}

		model.addAttribute("history",history);
		model.addAttribute("users",users);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));
		return "user-detail";
	}

	@RequestMapping(value="/user/user-loan-history.do", method=RequestMethod.GET)
	public String usersLoanHistory(Model model, HttpServletRequest req) 
	{
		Users users = new Users();
		List<History> history = null;
		List<Loan> loan = null;
		String email = usersService.getPrincipal().getUsername();
		try {
			users = usersService.detailByEmail(email);
			history = historyService.historyUser(users.getUserNo());
			loan = loanService.selectUserNo(users.getUserNo());

		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} catch (HistoryException e) {
			model.addAttribute("error", "server");
		} catch (LoanException e) {
			model.addAttribute("error", "serv er");
		}
		model.addAttribute("history",history);
		model.addAttribute("loan",loan);
		model.addAttribute("users",users);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));


		return "/user/user-loan";
	}

	// 회원탈퇴 
	@RequestMapping(value="/users-withdraw.do", method=RequestMethod.GET)
	public String userWithdraw(HttpServletRequest request) {
		Users user = new Users();
		try {
			String email = usersService.getPrincipal().getUsername();
			user = usersService.detailByEmail(email);
		} catch (UsersException e) {
			request.setAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		request.setAttribute("user", user);
		return "users-withdraw";
	}

	@RequestMapping(value="/users-withdraw.do", method=RequestMethod.POST)
	public String userWithdraw(HttpServletRequest request, HttpServletResponse resp,
			String email,
			String password) {
		try {
			Users user = usersService.detailByEmail(email);
			boolean pass = usersService.isPasswordMatched(password);
			if (!pass) {
				return "redirect:users-withdraw.do?error=password";
			}
			Integer userNo = user.getUserNo();
			usersService.updateMember(userNo);
			usersService.logout(request, resp);
		} catch (UsersException e) {
			request.setAttribute("error", "server");
			System.out.println(e.getMessage());
		}

		return "redirect:/index.do";
	}

	// 회원 현재 대출 현황 
	@RequestMapping(value="/user/user-loan-history1.do", method=RequestMethod.GET)
	public String usersLoanHistory1(Model model, Integer bookNo, String deadline, Integer userNo) 
	{
		Users users = new Users();
		Loan loan = null;
		BookList bookList = null;
		Calendar deadline0 = Calendar.getInstance();
		Date date;

		try {
			date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(deadline);
			users = usersService.detail(userNo);
			deadline0.setTime(date);
			deadline0.add(Calendar.DATE, 7);

			loan = new Loan(null, null, userNo, bookNo, null, deadline0.getTime(), true);
			loanService.extension(loan);
			bookList = bookListService.selectBook(bookNo);



		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} catch (LoanException e) {
			model.addAttribute("error", "server");
		} catch (BookException e) {
			model.addAttribute("error", "server");
		} catch (ParseException e) {
			model.addAttribute("error", "server");
		}

		model.addAttribute("users",users);
		model.addAttribute("loan",loan);
		model.addAttribute("bookList",bookList);


		return "/user/extension-confirm";
	}

	// 회원 예약 현황
	@RequestMapping(value="/user/user-reserv-list.do", method=RequestMethod.GET)
	public String loanReservList(Model model) {
		List<Loan> listLoan = null;
		List<Reservation> listreserv = null;
		Users user = new Users();
		try {
			String email = usersService.getPrincipal().getUsername();
			user = usersService.detailByEmail(email);
			Integer userNo = user.getUserNo();

			listLoan = loanService.reservloanList(userNo);
			listreserv = reservService.reservList(userNo);
		} catch (Exception e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("loan", listLoan);
		model.addAttribute("reserv", listreserv);

		return "/user/user-reserv-list";
	}

	// 전체 대출 기록 
	@RequestMapping(value="/user/user-history-date.do", method=RequestMethod.GET)
	public String usersHistory(Model model) 
	{
		Users users = new Users();
		List<History> historyList = null;
		String email = usersService.getPrincipal().getUsername();
		try {
			users = usersService.detailByEmail(email);


			historyList = historyService.historyUser(users.getUserNo());


		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} catch (HistoryException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("historyList",historyList);
		model.addAttribute("users",users);


		return "user/user-history-date";
	}
	//아이디 중복 확인
	@RequestMapping(value = "emailCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<Object, Object>> emailCheck(String email) {

		Users user=null;
		int count=0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			user = usersService.detailByEmail(email);
			if(user==null) 
			{
				count = 0;
			}else {
				count= user.getUserNo();
			}
			
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
			map.put("cnt", count);
		return new ResponseEntity<Map<Object,Object>>(map,HttpStatus.OK);
	}
	//비밀번호 확인 (비밀번호 변경)
	@RequestMapping(value = "/user/passwordCheck.do")
	@ResponseBody
	public Map<Object,Object> passwordCheck(String password)
	{
		Map<Object, Object> map = new HashMap<>();
		boolean pass= false;
		try {
			pass = usersService.isPasswordMatched(password);
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
		map.put("psw", pass);
		return map;
	}
	
	@Scheduled(cron="0 0 2 * * *")		// 매일 오전 2시 실행
	public void BREAKUSERS() 
	{
		Users users = new Users();
		List<Users> usersList = null;
		List<Loan> loanList = null;
		System.out.println("실행중...");
		try {

			loanList = loanService.selectDeadlineOut();

			for (Loan loan : loanList) {
				Integer userNo = loan.getUserNo();

				// 변경하려는 사용자의 권한을 입력 (일반 사용자 권한: 30, "BREAKUSERS")
				Authority auth = new Authority(AuthorityId.BREAKUSERS.getAuthorityId(), AuthorityId.BREAKUSERS.name());

				// Set 컬렉션을 이용하여 users 객체에 권한을 담기
				Set<Authority> auths = new HashSet<>();
				auths.add(auth);
				users.setAuthorities(auths);
				users.setUserNo(userNo);

				usersService.updateAuthority(users);
			}

			usersList = usersService.stopDateSelect();
			for (Users user : usersList) {

				// 변경하려는 사용자의 권한을 입력 (일반 사용자 권한: 20, "USER")
				Authority auth = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());

				// Set 컬렉션을 이용하여 users 객체에 권한을 담기
				Set<Authority> auths = new HashSet<>();
				auths.add(auth);
				users.setAuthorities(auths);
				users.setUserNo(user.getUserNo());

				usersService.updateAuthority(users);

				user.setStopDate(null);
				usersService.modify(user);
			}

		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}  
	}

}







