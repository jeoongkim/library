package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.ReadingRoomDao;
import library.model.ReadingRoomException;
import library.model.vo.ReadingRoom;
@Service
public class ReadingRoomServiceImpl implements ReadingRoomService {
	
	@Autowired
	private ReadingRoomDao dao;
	
	public ReadingRoomServiceImpl() {}

	@Override
	public void reservReadingRoom(ReadingRoom readingRoom) throws ReadingRoomException {
		dao.reservReadingRoom(readingRoom);
		
	}

	@Override
	public void returnReadingRoom(Integer userNo) throws ReadingRoomException {
		dao.returnReadingRoom(userNo);
		
	}

	@Override
	public void increaseReadingRoom(Integer userNo) throws ReadingRoomException {
		dao.increaseReadingRoom(userNo);
		
	}

	@Override
	public List<ReadingRoom> selectAll() throws ReadingRoomException {
		
		return dao.selectAll();
	}

	@Override
	public ReadingRoom selectusers(Integer userNo) throws ReadingRoomException {
		
		return dao.selectusers(userNo);
	}

	@Override
	public int reservationCount(Integer roomNo) throws ReadingRoomException {
		
		return dao.reservationCount(roomNo);
	}

}
