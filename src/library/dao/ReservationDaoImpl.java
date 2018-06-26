package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.BookException;
import library.model.ReservationException;
import library.model.vo.Reservation;
@Repository
public class ReservationDaoImpl implements ReservationDao
{
	private static final String MAPPER_NS=Reservation.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public ReservationDaoImpl() {}
	
	@Override
	public void reservationBook(Reservation reservation) throws ReservationException 
	{
		try {
			session.insert(MAPPER_NS+".insert-reservation",reservation);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ReservationException(e.getMessage());
		}
	}
	@Override
	public Integer reserNo(Integer userNo) throws ReservationException 
	{
		Integer reserNo=null;
		try {
			reserNo=session.selectOne(MAPPER_NS+".select-reservation",userNo);
		} catch (Exception e) {
			throw new ReservationException(e.getMessage());
		}
		return reserNo;
	}
	@Override
	public void deleteReservation(Integer reserNo) throws ReservationException 
	{
		try {
			session.delete(MAPPER_NS+".delete-reservation",reserNo);
		} catch (Exception e) {
			throw new ReservationException(e.getMessage());
		}
	}

	@Override
	public Reservation isbnReservation(String isbn) throws ReservationException {
		Reservation reser = null;
		try {
			reser = session.selectOne(MAPPER_NS+".select-isbn-reservation",isbn);
		} catch (Exception e) {
			throw new ReservationException(e.getMessage());
		}
		return reser;
	}

	@Override
	public int ReservationCount(String isbn) throws BookException {
		Integer count=null;
		try {
			count= session.selectOne(MAPPER_NS+".select-count-reservation",isbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<Reservation> selectList() throws ReservationException 
	{
		List<Reservation> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-list-reservation");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ReservationException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Reservation> selectJoinList() throws ReservationException {
		List<Reservation> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-joinList-reservation");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ReservationException(e.getMessage());
		}
		return list;
	}

	@Override
	public Integer totalreserv(Integer userNo) throws ReservationException {
		Integer count = null;
		try {
			count = session.selectOne(MAPPER_NS+".select-total-reservUser", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ReservationException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<Reservation> reservList(Integer userNo) throws ReservationException {
		List<Reservation> list = null;
		try {
			list=session.selectList(MAPPER_NS + ".select-reserv-list", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ReservationException(e.getMessage());
		}
		return list;
	}

}
