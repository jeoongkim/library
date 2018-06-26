package library.dao;

import java.util.List;

import library.model.HistoryException;
import library.model.vo.History;

public interface HistoryDao {

	public void insert(History history) throws HistoryException;		//히스토리 기록(대출시)
	public void update(History history) throws HistoryException;		//히스토리 수정(반납시)
	public List<History> HistoryUser (Integer userNo) throws HistoryException;		//히스토리 기록 조회
	public List<History> HistoryMonthTop10 () throws HistoryException;		//히스토리 top10 조회
	public List<History> HistoryUserDate (History history) throws HistoryException;		//유저 번호로 히스토리 조회(기한별로 조회 가능)
	
	
}
