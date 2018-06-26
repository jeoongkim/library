package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.vo.Score;
@Repository
public class ScoreDaoImpl implements ScoreDao{
	
	private static final String MAPPER_NS=Score.class.getName();

	@Autowired
	private SqlSession session;
	
	public ScoreDaoImpl() {}

	@Override
	public void insert(Score score) {
		
		session.insert(MAPPER_NS + ".insert-score", score);
		
	}

	@Override
	public List<Score> selectIsbn(String isbn) {
		
		List<Score> list = null;
		
		list=session.selectList(MAPPER_NS+".select-isbn-score", isbn);
		
		return list;
	}

	@Override
	public List<Score> selectUsersNo(Integer usersNo) {
		List<Score> list = null;
		
		list=session.selectList(MAPPER_NS+".select-userNo-score", usersNo);
		
		return list;
	}

	@Override
	public Integer countUserIsbn(String isbn) {
		Integer countUsers = null;
		
		countUsers = session.selectOne(MAPPER_NS+".select-countUser-score", isbn);
		
		return countUsers;
	}

}
