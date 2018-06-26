package library.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import library.model.BookException;
import library.model.NoticeException;
import library.model.UsersException;
import library.model.vo.AudioList;
import library.model.vo.Book;
import library.model.vo.BookList;
import library.model.vo.Notice;
import library.model.vo.Score;
import library.model.vo.Users;
import library.service.BookListService;
import library.service.BookService;
import library.service.FileService;
import library.service.NoticeService;
import library.service.ScoreService;
import library.service.UsersService;

@Controller
public class CommonWebController 
{
	@Autowired
	private FileService fileService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookListService bookListservice;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping(value="/book-list.do")
	public String list(Model model) 
	{
		List<Book> list=null;

		try {
			list=bookService.list();
		} catch (Exception e) {
			model.addAttribute("error","server");
		}
		model.addAttribute("list",list);
		return "book-list";
	}
	
	@RequestMapping(value="/book-detail.do", method=RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request, 
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		List<BookList> bookList = null;
		String filename = null;
		String imgPath = null;
		double bookscore = 0;
		Integer countUsers = 0;
		try {
			bookscore = scoreService.bookScore(isbn);
			countUsers = scoreService.countUserIsbn(isbn);
			
			book = bookService.detail(isbn);
		
			bookList = bookListservice.list(isbn);
			
			
			filename = book.getAttachment();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename,"UTF-8");
			}
			
			imgPath = fileService.getimgPath(request, filename);
			
		}catch (BookException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "incoding");
		}
		
		model.addAttribute("book", book);
		model.addAttribute("bookList", bookList);
		model.addAttribute("filename", filename);
		model.addAttribute("bookscore", bookscore);
		model.addAttribute("countUsers", countUsers);
		if (imgPath != null && !imgPath.trim().isEmpty()) {

			model.addAttribute("imgPath", imgPath);
		}
		
		return "book-detail";
	}
	@RequestMapping(value="/score.do", method=RequestMethod.GET)
	public String score(Integer bookScore, Model model, HttpServletRequest request, 
			@RequestParam(value="isbn", required=true) String isbn)
	{
		String email = usersService.getPrincipal().getUsername();
		try {
			Users user = usersService.detailByEmail(email);
			Score score = new Score(null, bookScore, isbn, user.getUserNo());
			List<Score> selectIsbn = scoreService.selectIsbn(isbn);
			
			for (Score item : selectIsbn) {
				if (item.getUserNo().equals(user.getUserNo())) {
					return "score-false";
				}
			}
			
			scoreService.insert(score);
			
		} catch (UsersException e) {
			model.addAttribute("error","server");
		}
		
		return "redirect:../Library/book-detail.do?isbn=" + isbn;
	}
	
	@RequestMapping(value="/book-search.do", method=RequestMethod.GET)
	public String searchBook(Model model, String input) 
	{
		List<Book> list=null;

		try {
			list=bookService.detailBook(input);
		} catch (Exception e) {
			model.addAttribute("error","server");
		}
		model.addAttribute("input", input);
		model.addAttribute("list",list);
		return "book-search";
	}
	// 도서번호 찾기 
	@RequestMapping(value="/book-search-ajax.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<Object,Object> findBookNum(String info)
	{
		Map<Object, Object> map =null;
		List<Book> list = null;
		try {
			list = bookService.detailBook(info);
			
			map.put("list", list);
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	// 공지사항
	@RequestMapping(value="/notice-list.do", method=RequestMethod.GET)
	public String noticeList(Model model) {
		List<Notice> list = null;
		try {
			list = noticeService.list();
		} catch (NoticeException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("list", list);
		return "notice-list";
	}
	
	@RequestMapping(value="/notice-detail.do", method=RequestMethod.GET)
	public String noticeDetail(Model model, @RequestParam(value="noticeNo", required=true) Integer noticeNo) {
		Notice notice = new Notice();
		try {
			notice = noticeService.detail(noticeNo);
		} catch (NoticeException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		model.addAttribute("notice", notice);
		return "notice-detail";
	}
	
	@RequestMapping(value="/admin/notice-add.do", method=RequestMethod.GET)
	public String noticeAdd (Model model) {
		String email = usersService.getPrincipal().getUsername();
		try {
			Users user = usersService.detailByEmail(email);
			user.setPassword(null);
			model.addAttribute("user", user);
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		return "/admin/notice-add";
	}
	
	@RequestMapping(value="/admin/notice-add.do",method=RequestMethod.POST)
	public String add(HttpServletRequest request, 
			String title,
			String description,
			Integer userNo) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setDescription(description);
		notice.setUserNo(userNo);
		
		try {
			noticeService.add(notice);
		} catch (NoticeException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		
		return "redirect:../notice-list.do";
	}
	
	@RequestMapping(value="/admin/notice-modify.do",method=RequestMethod.GET )
	public String moidfy(Model model, @RequestParam(value="noticeNo", required=true) Integer noticeNo) {
		Notice notice = new Notice();
		String email = usersService.getPrincipal().getUsername();
		try {
			Users user = usersService.detailByEmail(email);
			user.setPassword(null);
			model.addAttribute("user", user);
			notice = noticeService.detail(noticeNo);
		} catch (NoticeException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (UsersException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("notice", notice);
		return "/admin/notice-modify";
	}
	
	@RequestMapping(value="/admin/notice-modify.do",method=RequestMethod.POST)
	public String noticeModify(HttpServletRequest request, 
			String title,
			String description,
			Integer userNo,
			Integer noticeNo) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setDescription(description);
		notice.setUserNo(userNo);
		notice.setNoticeNo(noticeNo);
		
		try {
			noticeService.modify(notice);;
		} catch (NoticeException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		return "redirect:../notice-list.do";
	}
	
	@RequestMapping(value="/admin/notice-remove.do", method=RequestMethod.GET)
	public String remove(Model model, @RequestParam(value="noticeNo", required=true) Integer noticeNo) {
		model.addAttribute("noticeNo", noticeNo);
		return "/admin/notice-remove";
	}
	
	@RequestMapping(value="/admin/notice-remove.do", method=RequestMethod.POST)
	public String remove(Model model, Integer noticeNo, HttpServletRequest request) {
		try {
			noticeService.remove(noticeNo);
		} catch (NoticeException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "redirect:../notice-list.do";
	}
	
	// 신착도서
	@RequestMapping(value="/book-new.do", method=RequestMethod.GET)
	public String bookNew(Model model) {
		List<Book> list = null;
		try {
			list = bookService.listIsbnNo();
		} catch (BookException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("list", list);
		return "/book-new";
	}
	
	
	@RequestMapping(value="/introduce_home.do")
	public String Introduce_project() 
	{
		return "/introduce_home";
	}
	@RequestMapping(value="/introduce_status.do")
	public String Introduce_status() 
	{
		return "/introduce_status";
	}
	@RequestMapping(value="/introduce_time.do")
	public String Introduce_time() 
	{
		return "/introduce_time";
	}
	@RequestMapping(value="/introduce_user.do")
	public String Introduce_user() 
	{
		return "/introduce_user";
	}
	@RequestMapping(value="/introduce_apply_hope_book.do")
	public String Introduce_apply_hope_book() 
	{
		return "introduce_apply_hope_book";
	}
	
	// 오디오북 목록
	@RequestMapping(value="/book-audio-list", method=RequestMethod.GET)
	public String audioList(Model model) {
		try {
			List<Book> list = bookService.audioAllList();
			model.addAttribute("list", list);
		} catch (BookException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "/book-audio-list";
	}
	
	// 오디오북 상세보기
	@RequestMapping(value="/book-audio-detail", method=RequestMethod.GET)
	public String audioDetail(Model model, HttpServletRequest request, 
			@RequestParam(value="isbn", required=true) String isbn) {
		
		Book book = new Book();
		AudioList audio = new AudioList();
		String filename = null;
		String filename2 = null;
		String imgPath = null;
		String filePath = null;
		try {
			book = bookService.audioSelect(isbn);
			audio = bookListservice.selectAudio(isbn);
			
			filename = book.getAttachment();
			filename2 = audio.getFile();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
			} 
			if (filename2 != null && !filename2.trim().isEmpty()) {
				filename2 = URLDecoder.decode(filename2, "UTF-8");
			}
			
			imgPath = fileService.getimgPath(request, filename);
			filePath = fileService.getFilePath(request, filename2);
			
			model.addAttribute("audio", audio);
			model.addAttribute("book", book);
			
			if (imgPath != null && !imgPath.trim().isEmpty()) {
				model.addAttribute("imgPath", imgPath);
			} 
			if (filePath != null && !filePath.trim().isEmpty()) {
				model.addAttribute("filePath", filePath);
			}
		} catch (BookException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "/book-audio-detail";
	}
	
}






























