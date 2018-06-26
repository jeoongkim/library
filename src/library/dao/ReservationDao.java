package library.dao;

import java.util.List;

import library.model.BookException;
import library.model.ReservationException;
import library.model.vo.Reservation;

public interface ReservationDao 
{
	public void reservationBook(Reservation reservation) throws ReservationException;
	public void deleteReservation(Integer reserNo) throws ReservationException;
	public Integer reserNo(Integer userNo) throws ReservationException;
	public Reservation isbnReservation(String isbn) throws ReservationException;
	public int ReservationCount(String isbn) throws BookException;	// 같은 isbn을 예약한 사람 수
	public List<Reservation> selectList() throws ReservationException;
	public List<Reservation> selectJoinList() throws ReservationException;
	
	public Integer totalreserv(Integer userNo) throws ReservationException;
	public List<Reservation> reservList(Integer userNo) throws ReservationException;
}
