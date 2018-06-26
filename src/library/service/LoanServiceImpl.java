package library.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.LoanDao;
import library.dao.UsersDao;
import library.model.BookException;
import library.model.LoanException;
import library.model.UsersException;
import library.model.vo.History;
import library.model.vo.Loan;
import library.model.vo.Users;

@Service
@Transactional
public class LoanServiceImpl implements LoanService
{
	@Autowired
	private LoanDao dao;
	@Autowired
	private UsersDao userDao;
	@Autowired
	private MailService mailService;
	
	public LoanServiceImpl() {}
	
	@Override
	public void loanBook(Loan loan) throws LoanException 
	{
		dao.loanBook(loan);
	}
	
	@Override
	public Loan loanNum(Integer bookNum) throws LoanException 
	{
		return dao.loanNum(bookNum);
	}
	
	@Override
	public void returnBook(Integer loanNum) throws LoanException 
	{
		dao.returnBook(loanNum);
	}

	@Override
	public void insertReservLoan(Loan loan) throws LoanException {
		
		dao.insertReservLoan(loan);
	}

	@Override
	public void updateReservLoan(Integer loanNum) throws LoanException {
		
		dao.updateReservLoan(loanNum);
	}

	@Override
	public List<Loan> selectAll() throws LoanException 
	{
		return dao.selectList();
	}

	@Override
	public List<Loan> selectJoinList() throws LoanException {
		
		return dao.selectJoinList();
	}

	@Override
	public void deadlineReturnEmail() throws LoanException {
		try {
			List<Loan> list = dao.selectDeadline();
			if (list != null && !list.isEmpty()) {
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
				    Loan item = (Loan) iterator.next();
				    Integer userNo = item.getUserNo();
				    Users user = userDao.select(userNo);
				    user.setPassword(null);
					mailService.retrunEmail(user);
				}
			}
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Loan> selectDeadlinereserv() throws LoanException {
		
		return dao.selectDeadlinereserv();
	}

	@Override
	public void extension(Loan loan) throws LoanException {
		dao.extension(loan);
		
	}

	@Override
	public List<Loan> selectUserNo(Integer userNo) throws LoanException {
		
		return dao.selectUserNo(userNo);
	}
	
	@Override
	public void insertReserv(Integer userNo, Integer bookNo)  {
		Calendar deadline = Calendar.getInstance();
		deadline.setTime(new Date());
		deadline.add(Calendar.DATE, 4);
		Loan loan=new Loan();
		try {
			loan.setBookNo(bookNo);
			loan.setUserNo(userNo);
			loan.setDeadline(deadline.getTime());
			insertReservLoan(loan);
			
		} catch (LoanException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Integer totalReserv(Integer userNo) throws LoanException {
		return dao.totalReserv(userNo);
	}

	@Override
	public List<Loan> reservloanList(Integer userNo) throws LoanException {
		return dao.reservloanList(userNo);
	}
	
	public List<Loan> selectSearchUserNo(String input) throws LoanException {
		return dao.selectSearchUserNo(input);
	}

	@Override
	public List<Loan> selectDeadlineOut() throws LoanException {
		
		return dao.selectDeadlineOut();
	}
}
