package library.service;

import java.util.List;

import library.model.vo.Score;

public interface ScoreService {
	
	public void insert (Score score);
	public List<Score> selectIsbn(String isbn);
	public List<Score> selectUsersNo(Integer usersNo);
	public Integer countUserIsbn (String isbn);
	public double bookScore (String isbn);

}
