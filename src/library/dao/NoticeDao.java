package library.dao;

import java.util.List;

import library.model.NoticeException;
import library.model.vo.Notice;

public interface NoticeDao {
	public void insert(Notice notice) throws NoticeException;
	public Notice select(Integer noticeNo) throws NoticeException;
	public List<Notice> selectAll() throws NoticeException;
	public void update(Notice notice) throws NoticeException;
	public void delete(Integer noticeNo) throws NoticeException;

}
