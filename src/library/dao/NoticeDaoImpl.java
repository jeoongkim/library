package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.NoticeException;
import library.model.vo.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	private static final String MAPPER_NS = Notice.class.getName();

	@Autowired
	private SqlSession session;
	
	public NoticeDaoImpl() {}
	
	@Override
	public void insert(Notice notice) throws NoticeException {
		try {
			session.insert(MAPPER_NS + ".insert-notice", notice);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new NoticeException(e.getMessage());
		}
	}

	@Override
	public Notice select(Integer noticeNo) throws NoticeException {
		Notice notice = null;
		try {
			notice = session.selectOne(MAPPER_NS + ".select-notice", noticeNo);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new NoticeException(e.getMessage());
		}
		return notice;
	}

	@Override
	public List<Notice> selectAll() throws NoticeException {
		List<Notice> notice = null;
		try {
			notice = session.selectList(MAPPER_NS + ".select-all-notice");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new NoticeException(e.getMessage());
		}
		return notice;
	}

	@Override
	public void update(Notice notice) throws NoticeException {
		try {
			session.update(MAPPER_NS + ".update-notice", notice);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new NoticeException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer noticeNo) throws NoticeException {
		try {
			session.delete(MAPPER_NS + ".delete-notice", noticeNo);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new NoticeException(e.getMessage());
		}
	}

}
