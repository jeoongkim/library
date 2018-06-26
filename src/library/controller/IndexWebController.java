package library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.model.HistoryException;
import library.model.NoticeException;
import library.model.vo.History;
import library.model.vo.Notice;
import library.service.FileService;
import library.service.HistoryService;
import library.service.NoticeService;

@Controller
public class IndexWebController {
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value= {"/", "/index.do"}, method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest req) {	
		
		List<History> history = null;
		List<Notice> list = null;

		try {
			history = historyService.HistoryMonthTop10();
			list = noticeService.list();
			
		} catch (HistoryException e) {
			model.addAttribute("error", "server");
		} catch (NoticeException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("history", history);
		model.addAttribute("list", list);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));
		
		return "index";
	}	
	@RequestMapping(value="/test.do",method=RequestMethod.GET)
	public String test() 
	{
		return "test";
	}
}
