package library.service;

import java.util.List;

import library.model.NoticeException;
import library.model.vo.Notice;

public interface NoticeService {
	public void add(Notice notice) throws NoticeException;
	public Notice detail(Integer noticeNo) throws NoticeException;
	public List<Notice> list() throws NoticeException;
	public void modify(Notice notice) throws NoticeException;
	public void remove(Integer noticeNo) throws NoticeException;
}
