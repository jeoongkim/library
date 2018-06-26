package library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import library.model.LoanException;
import library.service.LoanService;

@Component
public class MailController {
	
	@Autowired
	private LoanService service;

	// 0/30 * * * * *      30초마다 실행
	// 0 0 9 * * ?
	
	@Scheduled(cron="0 0 9 * * *")		// 매일 오전 9시 실행
	public void returnEmail () {
		try {
			service.deadlineReturnEmail();
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		}
	}
}


