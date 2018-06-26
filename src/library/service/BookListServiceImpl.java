package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.BookListDao;
import library.model.BookException;
import library.model.vo.AudioList;
import library.model.vo.Book;
import library.model.vo.BookList;
@Service
public class BookListServiceImpl implements BookListService{
	
	@Autowired
	private BookListDao dao;
	
	public BookListServiceImpl() {}

	@Override
	public void add(BookList bookList) throws BookException {
		
		dao.insert(bookList);
	}

	@Override
	public BookList detail(Integer bookNo) throws BookException {
		
		return dao.select(bookNo);
	}

	@Override
	public List<BookList> list(String isbn) throws BookException {
		
		return dao.selectIsbn(isbn);
	}

	@Override
	public int count() throws BookException 
	{
		return dao.bookCount();
	}

	@Override
	public int count(String isbn) throws BookException 
	{
		return dao.bookCount(isbn);
	}

	@Override
	public void modify(BookList bookList) throws BookException 
	{
		dao.update(bookList);
	}

	@Override
	public void remove(Integer bookNo) throws BookException 
	{
		dao.delete(bookNo);
	}

	@Override
	public void loanBookList(Integer bookNo) throws BookException {
		
		dao.loanBookList(bookNo);
	}

	@Override
	public void returnBookList(Integer bookNo) throws BookException {
		
		dao.returnBookList(bookNo);
	}

	@Override
	public int whetherCount(String isbn) throws BookException {
		
		return dao.whetherCount(isbn);
	}

	@Override
	public BookList selectBook(Integer bookNo) throws BookException {
		
		return dao.selectBook(bookNo);
	}

	@Override
	public AudioList selectAudio(String isbn) throws BookException {
		return dao.selectAudio(isbn);
	}

	@Override
	public void insertAudio(AudioList audio) throws BookException {
		dao.insertAudio(audio);
	}

	@Override
	public void deleteAudio(String isbn) throws BookException {
		dao.deleteAudio(isbn);
	}

	@Override
	public String updateAudio(AudioList audio) throws BookException {
		AudioList item = dao.selectAudio(audio.getIsbn());
		String filename = item.getFile();
		dao.updateAudio(audio);
		return filename;
	}

}
