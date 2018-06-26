package library.service;

import java.util.List;

import library.model.BookException;
import library.model.vo.Book;
import library.model.vo.BookApply;
import library.model.vo.BookList;
import library.model.vo.Loan;

public interface BookService 
{
	public void add(Book book) throws BookException;
	public Book detail(String isbn) throws BookException;
	public List<Book> list() throws BookException;
	public int count() throws BookException;
	public String modify(Book book) throws BookException;
	public String remove(String isbn) throws BookException;
	public List<Book> detailBook(String input) throws BookException;
	public List<Book> listIsbnNo() throws BookException;
	
	public List<BookApply> applyAlllist() throws BookException;
	public void insert(BookApply bookapply) throws BookException;
	public List<BookApply> listByuserNo(Integer userNo) throws BookException;
	public BookApply applyDetail(Integer applyNo) throws BookException;
	public void applyupdate(BookApply bookapply) throws BookException;
	public Integer monthCount(Integer userNo) throws BookException;
	
	public List<Book> audioAllList() throws BookException;
	public Book audioSelect(String isbn) throws BookException;
	
}
