package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.model.LoanException;
import library.model.ReservationException;
import library.model.vo.Loan;

@Repository
public class LoanDaoImpl implements LoanDao
{
	private static final String MAPPER_NS= Loan.class.getName();
	@Autowired
	private SqlSession session;
	
	public LoanDaoImpl() {}
	
	@Override
	public void loanBook(Loan loan) throws LoanException 
	{
		try {
			session.insert(MAPPER_NS+".insert-loan",loan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
	}
	@Override
	public Loan loanNum(Integer bookNum) throws LoanException 
	{
		Loan loan=null;
		try {
			
			loan=session.selectOne(MAPPER_NS+".select-loan-bookNo",bookNum);
			
		} catch (Exception e) {
			throw new LoanException(e.getMessage());
		}
		return loan;
	}
	
	@Override
	public void returnBook(Integer loanNum) throws LoanException 
	{
		try {
			session.delete(MAPPER_NS+".delete-loan",loanNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
	}

	@Override
	public void insertReservLoan(Loan loan) throws LoanException {
		try {
			session.insert(MAPPER_NS+".insert-reserv-loan",loan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		
	}

	@Override
	public void updateReservLoan(Integer loanNum) throws LoanException {
		try {
			session.update(MAPPER_NS+".update-reserv-loan",loanNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		
	}

	@Override
	public List<Loan> selectList() throws LoanException 
	{
		List<Loan> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-list-loan");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Loan> selectJoinList() throws LoanException {
		List<Loan> list=null;
		try {
			list=session.selectList(MAPPER_NS+".select-joinList-loan");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Loan> selectDeadline() throws LoanException {
		List<Loan> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-deadline-email");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Loan> selectDeadlinereserv() throws LoanException {
		List<Loan> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-deadline-reserv");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public void extension(Loan loan) throws LoanException {
		try {
			session.update(MAPPER_NS+".update-extension-loan",loan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		
	}

	@Override
	public List<Loan> selectUserNo(Integer userNo) throws LoanException {
		List<Loan> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-userNo-loan", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public Integer totalReserv(Integer userNo) throws LoanException {
		Integer count = null;
		try {
			count = session.selectOne(MAPPER_NS+".select-total-loan-reservUser", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<Loan> reservloanList(Integer userNo) throws LoanException {
		List<Loan> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-loan-reserv", userNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	public List<Loan> selectSearchUserNo(String input) throws LoanException {
		List<Loan> list = null;
		String input1=null;
		try {
			input1="%"+input+"%";
			list = session.selectList(MAPPER_NS + ".select-search-userNo-loan", input1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Loan> selectDeadlineOut() throws LoanException {
		List<Loan> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-deadline-out-loan");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LoanException(e.getMessage());
		}
		return list;
	}

	
}
