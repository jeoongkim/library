package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.ScoreDao;
import library.model.vo.Score;
@Service
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public void insert(Score score) {
		scoreDao.insert(score);
		
	}

	@Override
	public List<Score> selectIsbn(String isbn) {
		
		return scoreDao.selectIsbn(isbn);
	}

	@Override
	public List<Score> selectUsersNo(Integer usersNo) {
		
		return scoreDao.selectUsersNo(usersNo);
	}

	@Override
	public Integer countUserIsbn(String isbn) {
		
		Integer countUserIsbn = scoreDao.countUserIsbn(isbn);
		/*if (countUserIsbn == 0 && countUserIsbn.equals(null)) {
			return 0;
		}*/
		
		return countUserIsbn;
	}

	@Override
	public double bookScore(String isbn) {
		Integer bookScoreAll = 0;
		
		
		List<Score> selectIsbn = scoreDao.selectIsbn(isbn);
		Integer countUserIsbn = selectIsbn.size();
        
		for (Score list : selectIsbn) {
			bookScoreAll += list.getBookScore();
		}
		
		if ( bookScoreAll == 0 || countUserIsbn == 0 ) {
			return 0;
		}
		double bookScore = (double) bookScoreAll / countUserIsbn;
		bookScore = Math.round(bookScore*100)/100.0;
		
		return bookScore;
	}

}
