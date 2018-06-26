package library.service;

import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.Loan;
import library.model.vo.Users;

public interface MailService {
	
	public void sendEmail(final Users users);
	public void retrunEmail(final Users user);
	public void warehousing(final Users user, BookApply bookapply);
	public void nextLoanResrv(final Users user, Book book, Loan loanUser);
}
