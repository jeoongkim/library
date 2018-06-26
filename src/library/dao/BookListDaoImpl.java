package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.BookException;
import library.model.vo.AudioList;
import library.model.vo.Book;
import library.model.vo.BookList;

@Repository
public class BookListDaoImpl implements BookListDao{

	private static final String MAPPER_NS= BookList.class.getName();
	private static final String MAPPER_LIST = AudioList.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public BookListDaoImpl() {}
	
	@Override
	public void insert(BookList bookList) throws BookException {
		
		try {
			session.insert(MAPPER_NS+".insert-bookList",bookList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public List<BookList> selectIsbn(String isbn) throws BookException {
		List<BookList> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-bookList", isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public BookList select(Integer bookNo) throws BookException {
		BookList bookList = null;
		try {
			bookList = session.selectOne(MAPPER_NS+".select-bookList-no", bookNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return bookList;
	}

	@Override
	public int bookCount() throws BookException 
	{
		Integer count=null;
		try {
			count=session.selectOne(MAPPER_NS+".select-count-bookList");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public int bookCount(String isbn) throws BookException 
	{
		Integer count=null;
		try {
			count= session.selectOne(MAPPER_NS+".select-count-bookList-isbn",isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public void update(BookList bookList) throws BookException 
	{
		try {
			session.update(MAPPER_NS+".update-bookList",bookList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer bookNo) throws BookException 
	{
		try {
			session.delete(MAPPER_NS+".delete-bookList",bookNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void loanBookList(Integer bookNo) throws BookException {
		try {
			session.update(MAPPER_NS+".loan-bookList", bookNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public void returnBookList(Integer bookNo) throws BookException {
		try {
			session.update(MAPPER_NS+".return-bookList", bookNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public int whetherCount(String isbn) throws BookException {
		Integer count=null;
		try {
			count=session.selectOne(MAPPER_NS+".select-count-bookList-whether",isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public BookList selectBook(Integer bookNo) throws BookException {
		BookList bookList = null;
		try {
			bookList = session.selectOne(MAPPER_NS+".select-bookList-book-no", bookNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return bookList;
	}

	@Override
	public AudioList selectAudio(String isbn) throws BookException {
		AudioList audio = new AudioList();
		try {
			audio = session.selectOne(MAPPER_LIST + ".select-audioList", isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return audio;
	}

	@Override
	public void insertAudio(AudioList audio) throws BookException {
		try {
			session.insert(MAPPER_LIST + ".insert-audioList", audio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void deleteAudio(String isbn) throws BookException {
		try {
			session.delete(MAPPER_LIST + ".delete-audioList", isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void updateAudio(AudioList audio) throws BookException {
		try {
			session.update(MAPPER_LIST + ".update-audioList", audio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	
}
