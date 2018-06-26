package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.ReadingRoomException;
import library.model.vo.ReadingRoom;
@Repository
public class ReadingRoomDaoImpl implements ReadingRoomDao {
	
	private static final String MAPPER_NS= ReadingRoom.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public ReadingRoomDaoImpl() {}

	@Override
	public void reservReadingRoom(ReadingRoom readingRoom) throws ReadingRoomException {
		
		try {
			session.update(MAPPER_NS + ".update-reserv-ReadingRoom",readingRoom);
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		
	}

	@Override
	public void returnReadingRoom(Integer userNo) throws ReadingRoomException {
		try {
			session.update(MAPPER_NS + ".update-return-ReadingRoom",userNo);
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		
	}

	@Override
	public void increaseReadingRoom(Integer userNo) throws ReadingRoomException {
		try {
			session.update(MAPPER_NS + ".update-increase-ReadingRoom",userNo);
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		
	}

	@Override
	public List<ReadingRoom> selectAll() throws ReadingRoomException {
		List<ReadingRoom> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-all-ReadingRoom");
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		return list;
	}

	@Override
	public ReadingRoom selectusers(Integer userNo) throws ReadingRoomException {
		ReadingRoom item = new ReadingRoom();
		try {
			item = session.selectOne(MAPPER_NS + ".select-user-ReadingRoom", userNo);
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		return item;
	}

	@Override
	public int reservationCount(Integer roomNo) throws ReadingRoomException {
		Integer count = null;
		try {
			count = session.selectOne(MAPPER_NS + ".select-countReservation-ReadingRoom", roomNo);
		}catch (Exception e){
			throw new ReadingRoomException(e.getMessage());
		}
		return count;
	}

}
