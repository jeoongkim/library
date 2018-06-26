package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.HistoryDao;
import library.model.HistoryException;
import library.model.vo.History;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryDao dao;
	
	public HistoryServiceImpl() {}

	@Override
	public void insertHistory(History history) throws HistoryException {
		dao.insert(history);
		
	}

	@Override
	public void updateHistory(History history) throws HistoryException {
		dao.update(history);
		
	}

	@Override
	public List<History> historyUser(Integer userNo) throws HistoryException {
		
		return dao.HistoryUser(userNo);
	}

	@Override
	public List<History> HistoryMonthTop10() throws HistoryException {
		
		return dao.HistoryMonthTop10();
	}

	@Override
	public List<History> HistoryUserDate(History history) throws HistoryException {
		
		return dao.HistoryUserDate(history);
	}

}
