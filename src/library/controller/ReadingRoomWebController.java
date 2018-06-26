package library.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.model.ReadingRoomException;
import library.model.UsersException;
import library.model.vo.ReadingRoom;
import library.model.vo.Users;
import library.service.ReadingRoomService;
import library.service.UsersService;

@Controller
@RequestMapping("/user")
public class ReadingRoomWebController {
	
	@Autowired
	private ReadingRoomService readingRoomService;
	
	@Autowired
	private UsersService usersService;
	
	//열람실 신청 
	@RequestMapping(value="readingRoom-add.do",method=RequestMethod.GET)
	public String readingRoomreserv(Model model) {
		
		try {
			List<ReadingRoom> ReadingRoomList = readingRoomService.selectAll();
			
			
			model.addAttribute("ReadingRoomList", ReadingRoomList);
		} catch (ReadingRoomException e) {
			model.addAttribute("error", "server");
		} 
		
		return "/user/readingroom-add";
	}
	
	@RequestMapping(value="readingRoom-add.do",method=RequestMethod.POST)
	public String readingRoomreserv(Model model, Integer roomNo, Integer seatNo) {
		Users users = new Users();
		String email = usersService.getPrincipal().getUsername();
		try {
			List<ReadingRoom> ReadingRoomList = readingRoomService.selectAll();
			users = usersService.detailByEmail(email);
			ReadingRoom readingRoom = new ReadingRoom(null, users.getUserNo(), roomNo, seatNo, true, null);
			
			for ( ReadingRoom room : ReadingRoomList) {
				if (room.getUserNo() != null && room.getUserNo().equals(users.getUserNo())) {
					model.addAttribute("name", users.getName());
					return "/user/readingRoom-users-false";
				}
			}
			
			for ( ReadingRoom room : ReadingRoomList) {
				
				if (room.getRoomNo().equals(roomNo) && room.getSeatNo().equals(seatNo) && room.isReservation()) {
					model.addAttribute("roomNo", room.getRoomNo());
					model.addAttribute("seatNo", room.getSeatNo());
					return "/user/readingRoom-seat-false";
				}
			}
			
			readingRoomService.reservReadingRoom(readingRoom);
			model.addAttribute("roomNo", roomNo);
			model.addAttribute("seatNo", seatNo);
			
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} catch (ReadingRoomException e) {
			model.addAttribute("error", "server");
		}
		
		return "/user/room-confirm";
	}
	
	// 열람실 연장
	@RequestMapping(value="readingRoom-increase.do",method=RequestMethod.POST)
	public String readingRoomincrease(Model model) {
		Users users = new Users();
		String email = usersService.getPrincipal().getUsername();
		try {
			users = usersService.detailByEmail(email);
			List<ReadingRoom> list = readingRoomService.selectAll();
			for(ReadingRoom room:list) {
				if (room.getUserNo() != null && room.getUserNo().equals(users.getUserNo())) {
					model.addAttribute("name", users.getName());
					readingRoomService.increaseReadingRoom(users.getUserNo());
					return "/user/increase-confirm";
				}
			}
			
			
			model.addAttribute("name", users.getName());
			
		} catch (ReadingRoomException e) {
			model.addAttribute("error", "server");
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} 
		
		return "/user/readingRoom-select-users-false";
	}
	
	// 열람실 반환
	@RequestMapping(value="readingRoom-return.do",method=RequestMethod.POST)
	public String readingRoomreturn(Model model) {
		Users users = new Users();
		String email = usersService.getPrincipal().getUsername();
		try {
			users = usersService.detailByEmail(email);
			List<ReadingRoom> list = readingRoomService.selectAll();
			for(ReadingRoom room:list) {
				if (room.getUserNo() != null && room.getUserNo().equals(users.getUserNo())) {
					model.addAttribute("name", users.getName());
					readingRoomService.returnReadingRoom(users.getUserNo());
					return "/user/return-confirm";
				}
			}
			
			
			model.addAttribute("name", users.getName());
			
		} catch (ReadingRoomException e) {
			model.addAttribute("error", "server");
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} 
		
		return "/user/readingRoom-select-users-false";
	}
	
	// 열람실 조회
	@RequestMapping(value="readingRoom-select.do",method=RequestMethod.GET)
	public String readingRoomselect(Model model) {
		Users users = new Users();
		String email = usersService.getPrincipal().getUsername();
		try {
			users = usersService.detailByEmail(email);
			ReadingRoom readingRoom = readingRoomService.selectusers(users.getUserNo());
			if (readingRoom == null) {
				model.addAttribute("name", users.getName());
				return "/user/readingRoom-select-users-false";
			}
			
			model.addAttribute("name", users.getName());
			model.addAttribute("readingRoom", readingRoom);
			
		} catch (ReadingRoomException e) {
			model.addAttribute("error", "server");
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		} 
		
		return "/user/readingRoom-select-user";
	}
	
	@RequestMapping(value="readingRoom.do",method=RequestMethod.GET)
	public String readingRoom() {
	
		return "user/readingRoom";
	}
	@RequestMapping(value="readingRoom.do",method=RequestMethod.POST)
	public String readingRoom(Model model) {
		
		return "/user/readingRoom";
	}
	
	
	@Scheduled(cron="0 0/1 * * * *")		// 매 분 실행
	public void returnreadingRoom () {
		Date date = new Date();
		try {
			List<ReadingRoom> allList = readingRoomService.selectAll();
			System.out.println(allList);
			for (ReadingRoom readingRoom : allList) {
				if (readingRoom.getTime() != null && readingRoom.getTime().before(date)) {
					readingRoomService.returnReadingRoom(readingRoom.getUserNo());
				}
			}			
		} catch (ReadingRoomException e) {
			System.out.println(e.getMessage());
		}
	}
}
