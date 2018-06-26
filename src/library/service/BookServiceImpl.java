package library.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.BookDao;
import library.dao.LoanDao;
import library.model.BookException;
import library.model.LoanException;
import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.BookList;
import library.model.vo.History;
import library.model.vo.Loan;
import library.model.vo.Reservation;
@Service
@Transactional
public class BookServiceImpl implements BookService
{
	@Autowired
	private BookDao dao;
	
	public BookServiceImpl() {}
	
	@Override
	public void add(Book book) throws BookException 
	{
		dao.insert(book);
	}

	@Override
	public Book detail(String isbn) throws BookException {
		return dao.select(isbn);
	}

	@Override
	public List<Book> list() throws BookException 
	{
		return dao.selectAll();
	}

	@Override
	public int count() throws BookException 
	{
		return dao.bookCount();
	}

	@Override
	public String modify(Book book) throws BookException 
	{
		Book item = dao.select(book.getIsbn());
		String filename = item.getAttachment();
		dao.update(book);
		
		return filename;
	}

	@Override
	public String remove(String isbn) throws BookException 
	{
		Book book = dao.select(isbn);
		String filename = book.getAttachment();
		dao.delete(isbn);
		
		return filename;
	}

	@Override
	public List<Book> detailBook(String input) throws BookException 
	{
		return dao.selectBook(input);
	}

	@Override
	public List<BookApply> applyAlllist() throws BookException {
		return dao.applySelectAll();
	}

	@Override
	public void insert(BookApply bookapply) throws BookException {
		dao.applyinsert(bookapply);
	}

	@Override
	public List<BookApply> listByuserNo(Integer userNo) throws BookException {
		return dao.listByUserNo(userNo);
	}

	@Override
	public BookApply applyDetail(Integer applyNo) throws BookException {
		return dao.applyDetail(applyNo);
	}

	@Override
	public List<Book> listIsbnNo() throws BookException {
		return dao.listIsbnNo();
	}

	@Override
	public void applyupdate(BookApply bookapply) throws BookException {
		dao.applyupdate(bookapply);
	}

	@Override
	public Integer monthCount(Integer userNo) throws BookException {
		return dao.monthCount(userNo);
	}

	@Override
	public List<Book> audioAllList() throws BookException {
		return dao.audioAllList();
	}

	@Override
	public Book audioSelect(String isbn) throws BookException {
		return dao.audioSelect(isbn);
	}

}
