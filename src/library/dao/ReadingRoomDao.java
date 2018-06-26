package library.dao;

import java.util.List;

import library.model.ReadingRoomException;
import library.model.vo.ReadingRoom;

public interface ReadingRoomDao {
	
	public void reservReadingRoom (ReadingRoom readingRoom) throws ReadingRoomException;
	public void returnReadingRoom (Integer userNo) throws ReadingRoomException;
	public void increaseReadingRoom (Integer userNo) throws ReadingRoomException;
	public List<ReadingRoom> selectAll () throws ReadingRoomException;
	public ReadingRoom selectusers(Integer userNo) throws ReadingRoomException;
	public int reservationCount(Integer roomNo) throws ReadingRoomException;				

}
