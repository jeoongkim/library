package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.HistoryException;
import library.model.vo.History;

@Repository
public class HistoryDaoImpl implements HistoryDao {

	private static final String MAPPER_NS = History.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public HistoryDaoImpl() {}
	
	@Override
	public void insert(History history) throws HistoryException {
		try {
			session.insert(MAPPER_NS + ".insert-history", history);
			
		} catch (Exception e) {
			throw new HistoryException(e.getMessage());
		}
		
		
	}

	@Override
	public void update(History history) throws HistoryException {
		try {
			session.update(MAPPER_NS + ".update-history", history);
			
		} catch (Exception e) {
			throw new HistoryException(e.getMessage());
		}
		
	}

	@Override
	public List<History> HistoryUser(Integer userNo) throws HistoryException {
		List<History> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".history-userNo", userNo);
			
		} catch (Exception e) {
			throw new HistoryException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<History> HistoryMonthTop10() throws HistoryException {
		List<History> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".history-month-top10");
			
		} catch (Exception e) {
			throw new HistoryException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<History> HistoryUserDate(History history) throws HistoryException {
		List<History> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".history-userNo-date", history);
			
		} catch (Exception e) {
			throw new HistoryException(e.getMessage());
		}
		return list;
	}

}
