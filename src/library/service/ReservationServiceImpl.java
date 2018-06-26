package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.ReservationDao;
import library.model.BookException;
import library.model.ReservationException;
import library.model.vo.Reservation;
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	private ReservationDao dao;
	
	public ReservationServiceImpl() {}
	
	
	@Override
	public void reservationBook(Reservation reservation) throws ReservationException 
	{
		dao.reservationBook(reservation);
	}

	@Override
	public Integer reserNo(Integer userNo) throws ReservationException 
	{
		return dao.reserNo(userNo);
	}
	
	@Override
	public void deleteReservation(Integer reserNo) throws ReservationException 
	{
		dao.deleteReservation(reserNo);
	}


	@Override
	public Reservation isbnReservation(String isbn) throws ReservationException {
		
		return dao.isbnReservation(isbn);
	}


	@Override
	public int ReservationCount(String isbn) throws BookException {
		
		return dao.ReservationCount(isbn);
	}


	@Override
	public List<Reservation> SelectAll() throws ReservationException 
	{
		return dao.selectList();
	}


	@Override
	public List<Reservation> selectJoinList() throws ReservationException {
		
		return dao.selectJoinList();
	}


	@Override
	public Integer totalReserv(Integer userNo) throws ReservationException {
		return dao.totalreserv(userNo);
	}


	@Override
	public List<Reservation> reservList(Integer userNo) throws ReservationException {
		return dao.reservList(userNo);
	}

}
