package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.NoticeDao;
import library.model.NoticeException;
import library.model.vo.Notice;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao dao;
	public NoticeServiceImpl() {}
	
	@Override
	public void add(Notice notice) throws NoticeException {
		dao.insert(notice);
	}

	@Override
	public Notice detail(Integer noticeNo) throws NoticeException {
		return dao.select(noticeNo);
	}

	@Override
	public List<Notice> list() throws NoticeException {
		return dao.selectAll();
	}

	@Override
	public void modify(Notice notice) throws NoticeException {
		dao.update(notice);
	}

	@Override
	public void remove(Integer noticeNo) throws NoticeException {
		dao.delete(noticeNo);
	}

}
