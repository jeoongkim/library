package library.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.Loan;
import library.model.vo.Users;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freeMarkerConfiguration;
	
	
	@Override
	public void sendEmail(Users user) {
		MimeMessagePreparator preparetor = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("joinhello.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("projectkoitt@gmail.com");		// 보내는 사람 이메일 주소
				helper.setTo(user.getEmail());				// 받는 사람 이메일 주소
				helper.setSubject("[희망도서관] 가입을 환영합니다.");		// 메일 제목
				helper.setText(text, true);					// 메일 내용, true는 HTML mail
			}
		};
		
		try {
			System.err.println("메일 보내는 중 >>> ");
			mailSender.send(preparetor);
			System.out.println("메일 보내기 완료 >>> ");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void retrunEmail(Users user) {
		MimeMessagePreparator preparetor = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("overdue.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("projectkoitt@gmail.com");		// 보내는 사람 이메일 주소
				helper.setTo(user.getEmail());				// 받는 사람 이메일 주소
				helper.setSubject("[희망도서관] 반납일 안내.");		// 메일 제목
				helper.setText(text, true);					// 메일 내용, true는 HTML mail
				
			}
		};
		try {
			System.err.println("메일 보내는 중 >>> ");
			mailSender.send(preparetor);
			System.out.println("메일 보내기 완료 >>> ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void warehousing(Users user, BookApply bookapply) {
		MimeMessagePreparator preparetor = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				model.put("book", bookapply);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("book-warehousing.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("projectkoitt@gmail.com");	// 보내는 사람 이메일 주소
				helper.setTo(user.getEmail());				// 받는 사람 이메일 주소
				helper.setSubject("[희망도서관] 희망도서 입고 안내.");		// 메일 제목
				helper.setText(text, true);					// 메일 내용, true는 HTML mail
			}
		};
		try {
			System.err.println("메일 보내는 중 >>> ");
			mailSender.send(preparetor);
			System.out.println("메일 보내기 완료 >>> ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void nextLoanResrv(Users user, Book book, Loan loanUser) {
		MimeMessagePreparator preparetor = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				model.put("book", book);
				model.put("loan", loanUser);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("book-return.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("projectkoitt@gmail.com");	// 보내는 사람 이메일 주소
				helper.setTo(user.getEmail());				// 받는 사람 이메일 주소
				helper.setSubject("[희망도서관] 예약도서 대출안내.");		// 메일 제목
				helper.setText(text, true);					// 메일 내용, true는 HTML mail
			}
		};
		try {
			System.err.println("메일 보내는 중 >>> ");
			mailSender.send(preparetor);
			System.out.println("메일 보내기 완료 >>> ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}


	
	
	
	
}






















