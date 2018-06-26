package library.service;

import java.util.List;

import library.model.HistoryException;
import library.model.vo.History;

public interface HistoryService {

	public void insertHistory (History history) throws HistoryException;
	public void updateHistory (History history) throws HistoryException;
	public List<History> historyUser(Integer userNo) throws HistoryException;
	public List<History> HistoryMonthTop10 () throws HistoryException;		//히스토리 기록 조회
	public List<History> HistoryUserDate (History history) throws HistoryException;		//유저 번호로 히스토리 조회(기한별로 조회 가능)
	
}
