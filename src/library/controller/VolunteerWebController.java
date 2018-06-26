package library.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import library.model.UsersException;
import library.model.VolunteerException;
import library.model.vo.Users;
import library.model.vo.Volunteer;
import library.service.UsersService;
import library.service.VolunteerService;

@Controller
@RequestMapping("/user")
public class VolunteerWebController {
	
	@Autowired
	private VolunteerService service;
	@Autowired
	private UsersService usersService;
	
	// 자원봉사
	@RequestMapping(value="/volunteer-list.do", method=RequestMethod.GET)
	public String listAll(Model model) {
		List<Volunteer> list = null;
		try {
			list = service.listAll();
		} catch (VolunteerException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("list", list);
		return "/user/volunteer-list";
	}
	
	// 글 조회시 비밀번호 입력 
	@RequestMapping(value="/volunteer-code.do", method=RequestMethod.GET)
	public String passwordCode(Model model, @RequestParam(value="volunNo", required=true) Integer volunNo) {
		Volunteer volun = new Volunteer();
		try {
			String email = usersService.getPrincipal().getUsername();
			Users nowuser = usersService.detailByEmail(email);
			volun = service.selectVolunNo(volunNo);
			Integer volunuser = volun.getUserNo();
			Users user = usersService.detail(volunuser);
			user.setPassword(null);
			String autho = nowuser.getAuthorities().toString();
			if(autho.equals("[ADMIN]")) {
				user.setPassword(null);
				model.addAttribute("volun", volun);
				model.addAttribute("user", user);
				return "/user/volunteer-detail";
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		model.addAttribute("volun", volun);
		return "/user/volunteer-code";
	}
	
	@RequestMapping(value="/volunteer-detail.do", method=RequestMethod.POST)
	public String detail(Model model, String textpassword, Integer volunNo) {
		try {
			Volunteer volun = service.selectVolunNo(volunNo);
			Integer userNo = volun.getUserNo();
			Users user = usersService.detail(userNo);
			user.setPassword(null);
			System.out.println(user);
			String password = volun.getPassword();
			if (!(password.equals(textpassword))) {
				return "redirect:/user/volunteer-code.do?error=password&volunNo="+volunNo;
			}
			model.addAttribute("volun", volun);
			model.addAttribute("user", user);
		} catch (Exception e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "/user/volunteer-detail";
	}
	
	@RequestMapping(value="/volunteer-add", method=RequestMethod.GET)
	public String add(Model model) {
		try {
			String email = usersService.getPrincipal().getUsername();
			Users user = usersService.detailByEmail(email);
			user.setPassword(null);
			model.addAttribute("user", user);
		} catch (UsersException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "/user/volunteer-add";
	}
	
	@RequestMapping(value="/volunteer-add", method=RequestMethod.POST)
	public String add(Model model, HttpServletRequest request,
			String title, String description, Integer userNo, String password, String applyDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date applyDate1;
		try {
			applyDate1 = transFormat.parse(applyDate);
			Volunteer volun = new Volunteer();
			volun.setTitle(title);
			volun.setDescription(description);
			volun.setUserNo(userNo);
			volun.setPassword(password);
			volun.setApplyDate(applyDate1);
			service.insert(volun);
		} catch (VolunteerException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		
		return "redirect:../user/volunteer-list.do";
	}
	
	@RequestMapping(value="/volunteer-code2", method=RequestMethod.GET)
	public String remove(Model model, @RequestParam(value="volunNo", required=true) Integer volunNo) {
		model.addAttribute("volunNo", volunNo);
		return "/user/volunteer-code2";
	}
	
	@RequestMapping(value="/volunteer-remove", method=RequestMethod.POST)
	public String remove(Model model, HttpServletRequest request,
			String textpassword, Integer volunNo) {
		try {
			Volunteer volun = service.selectVolunNo(volunNo);
			String password = volun.getPassword();
			if (!(password.equals(textpassword))) {
				return "redirect:/user/volunteer-code2.do?error=password&volunNo="+volunNo;
			}
			service.delete(volunNo);
		} catch (VolunteerException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		};
		return "redirect:../user/volunteer-list.do";
	}
	
	@RequestMapping(value="/volunteer-modify", method=RequestMethod.GET) 
	public String modify(Model model, @RequestParam(value="volunNo", required=true) Integer volunNo) {
		try {
			Volunteer volun = service.selectVolunNo(volunNo);
			Integer vuserNo = volun.getUserNo();
			String email = usersService.getPrincipal().getUsername();
			Users user = usersService.detailByEmail(email);
			Integer userNo = user.getUserNo();
			if (!(vuserNo.equals(userNo))) {
				return "/user/volunteer-modify-error";
			}
			user.setPassword(null);
			model.addAttribute("user", user);
			model.addAttribute("volun", volun);
		} catch (VolunteerException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (UsersException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "/user/volunteer-modify";
	}
	
	@RequestMapping(value="/volunteer-modify", method=RequestMethod.POST)
	public String modify(Model model, HttpServletRequest request, Integer volunNo,
			String title, String description, String applyDate, String password, Integer userNo) {
	
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date applyDate1;
		try {
			applyDate1 = transFormat.parse(applyDate);
			Volunteer volun = new Volunteer();
			volun.setTitle(title);
			volun.setDescription(description);
			volun.setApplyDate(applyDate1);
			volun.setPassword(password);
			volun.setUserNo(userNo);
			volun.setVolunNo(volunNo);
			service.update(volun);
		} catch (ParseException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (VolunteerException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "redirect:../user/volunteer-list.do";
	}

}
