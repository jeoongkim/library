package library.dao;


import java.util.List;

import library.model.BookException;
import library.model.vo.AudioList;
import library.model.vo.BookList;

public interface BookListDao {
	
	public void insert(BookList bookList) throws BookException;				// 북 추가
	public List<BookList> selectIsbn(String isbn) throws BookException; 	// isbn을 이용하여 책 정보 불러오기 
	public BookList select(Integer bookNo) throws BookException; 			// bookNo을 이용하여 책 정보 불러오기 
	public int bookCount() throws BookException;							// 전체 책 개수 가져오기
	public int bookCount(String isbn) throws BookException;					// isbn이 같은 책 개수 가져오기
	public void update(BookList bookList) throws BookException;				// 책 정보 수정
	public void delete(Integer bookNo) throws BookException;   				// 책정보 삭제
	public void loanBookList (Integer bookNo) throws BookException;			// 대출시 대출여부 false로 변환
	public void returnBookList (Integer bookNo) throws BookException;		// 반납시 대출여부 true로 변환
	public int whetherCount(String isbn) throws BookException;				// reservation의 true값 가져오기(0이면 예약가능)
	public BookList selectBook(Integer bookNo) throws BookException; 		// bookNo을 이용하여 책 정보 불러오기 
	
	public AudioList selectAudio(String isbn) throws BookException;
	public void insertAudio(AudioList audio) throws BookException;
	public void deleteAudio(String isbn) throws BookException;
	public void updateAudio(AudioList audio) throws BookException;
}
