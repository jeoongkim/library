package library.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatWebController {
	
	
	
	@RequestMapping(value="/user/chat-start.do", method=RequestMethod.GET)
	public String test (Model model) {
		
		return "chat-start";
	}
	@RequestMapping(value="/user/chat-start.do", method=RequestMethod.POST)
	public String test (Model model, String id) {
		
		model.addAttribute("id", id);
		
		return "chat-client";
	}
	
	
	

}
