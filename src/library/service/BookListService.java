package library.service;

import java.util.List;

import library.model.BookException;
import library.model.vo.AudioList;
import library.model.vo.Book;
import library.model.vo.BookList;

public interface BookListService {

	public void add(BookList bookList) throws BookException;
	public BookList detail(Integer bookNo) throws BookException;
	public List<BookList> list(String isbn) throws BookException;
	public int count() throws BookException;
	public int count(String isbn) throws BookException;
	public void modify(BookList bookList) throws BookException;
	public void remove(Integer bookNo) throws BookException;
	public void loanBookList (Integer bookNo) throws BookException;			// 대출시 대출여부 false로 변환
	public void returnBookList (Integer bookNo) throws BookException;		// 반납시 대출여부 true로 변환
	public int whetherCount(String isbn) throws BookException;				// reservation의 true값 가져오기(0이면 예약가능)

	public BookList selectBook(Integer bookNo) throws BookException; 		// bookNo을 이용하여 책 정보 불러오기 
	
	public AudioList selectAudio(String isbn) throws BookException;
	public void insertAudio(AudioList audio) throws BookException;
	public void deleteAudio(String isbn) throws BookException;
	public String updateAudio(AudioList audio) throws BookException;
}
