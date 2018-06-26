package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.BookException;
import library.model.vo.Book;
import library.model.vo.BookApply;
@Repository
public class BookDaoImpl implements BookDao
{
	private static final String MAPPER_NS= Book.class.getName();
	private static final String MAPPER_APPLY= BookApply.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public BookDaoImpl() {}
	
	@Override
	public void insert(Book book) throws BookException 
	{
		try {
			session.insert(MAPPER_NS+".insert-book",book);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public Book select(String isbn) throws BookException {
		Book book = null;
		try {
			book = session.selectOne(MAPPER_NS + ".select-book", isbn);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
		return book;
	}

	@Override
	public List<Book> selectAll() throws BookException 
	{
		List<Book> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-all-book");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public int bookCount() throws BookException 
	{
		Integer count=null;
		try {
			 count = session.selectOne(MAPPER_NS+".select-count-book");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public void update(Book book) throws BookException 
	{
		try {
			session.update(MAPPER_NS+".update-book",book);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public void delete(String isbn) throws BookException 
	{
		try {
			session.delete(MAPPER_NS+".delete-book",isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public Integer selectLastInsertId() throws BookException {
		Integer lastInsertId = null;
		
		try {
			lastInsertId = session.selectOne(MAPPER_NS + ".select-last-insert-id");
			
		}catch(Exception e) {
			throw new BookException(e.getMessage());
		}
		return lastInsertId;
	}

	@Override
	public List<Book> selectBook(String input1) throws BookException 
	{
		List<Book> book=null;
		String input=null;
		try {
			input="%"+input1+"%";
			book=session.selectList(MAPPER_NS+".select-book-input", input);
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		}
		return book;
	}

	@Override
	public List<BookApply> applySelectAll() throws BookException {
		List<BookApply> list=null;
		try {
			list = session.selectList(MAPPER_APPLY + ".select-all-apply");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public void applyinsert(BookApply bookapply) throws BookException {
		try {
			session.selectList(MAPPER_APPLY + ".insert-bookapply", bookapply);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public List<BookApply> listByUserNo(Integer userNo) throws BookException {
		List<BookApply> list = null;
		try {
			list = session.selectList(MAPPER_APPLY + ".select-userNo-list", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public BookApply applyDetail(Integer applyNo) throws BookException {
		BookApply book = null;
		try {
			book = session.selectOne(MAPPER_APPLY + ".select-book-apply", applyNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return book;
	}

	@Override
	public List<Book> listIsbnNo() throws BookException {
		List<Book> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".selec-list-isbnNo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public void applyupdate(BookApply bookapply) throws BookException {
		try {
			session.selectOne(MAPPER_APPLY + ".update-warehousing", bookapply);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
	}

	@Override
	public Integer monthCount(Integer userNo) throws BookException {
		Integer count = null;
		try{
			count = session.selectOne(MAPPER_APPLY + ".select-month-user", userNo);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<Book> audioAllList() throws BookException {
		List<Book> list = null;
		try {
			list=session.selectList(MAPPER_NS + ".select-allAudio-book");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return list;
	}

	@Override
	public Book audioSelect(String isbn) throws BookException {
		Book book = new Book();
		try {
			book = session.selectOne(MAPPER_NS + ".select-Audio-book", isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return book;
	}

	

}
