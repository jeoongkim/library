package library.dao;

import java.util.List;

import library.model.BookException;
import library.model.vo.Book;
import library.model.vo.BookApply;

public interface BookDao 
{
	public void insert(Book book) throws BookException;			// 북 추가
	public Book select(String isbn) throws BookException; 		// isbn을 이용하여 책 정보 불러오기 
	public List<Book> selectAll() throws BookException;			// 전체 책 정보 가져오기
	public int bookCount() throws BookException;				// 전체 책 개수 가져오기
	public void update(Book book) throws BookException;			// 책 정보 수정
	public void delete(String isbn) throws BookException;  	// 책정보 삭제
	public Integer selectLastInsertId() throws BookException;	//마지막 입력 isbn 가져오기
	public List<Book> selectBook(String input) throws BookException;
	public List<Book> listIsbnNo() throws BookException;		// isbn 역순으로 가져오긴
	
	public List<BookApply> applySelectAll() throws BookException;
	public void applyinsert(BookApply bookapply) throws BookException;	
	public List<BookApply> listByUserNo (Integer userNo) throws BookException;
	public BookApply applyDetail(Integer applyNo) throws BookException;
	public void applyupdate(BookApply bookapply) throws BookException;
	public Integer monthCount(Integer userNo) throws BookException;
	
	public List<Book> audioAllList() throws BookException;
	public Book audioSelect(String isbn) throws BookException;
}
