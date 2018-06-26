package library.dao;

import java.util.List;

import library.model.LoanException;
import library.model.vo.Loan;

public interface LoanDao 
{
	public void loanBook(Loan loan) throws LoanException;
	public void returnBook(Integer loanNum) throws LoanException;
	public Loan loanNum(Integer bookNum) throws LoanException;
	public void insertReservLoan(Loan loan) throws LoanException;
	public void updateReservLoan(Integer loanNum) throws LoanException;
	public List<Loan> selectList() throws LoanException;
	public List<Loan> selectJoinList() throws LoanException;
	public List<Loan> selectDeadline() throws LoanException;
	public List<Loan> selectDeadlinereserv() throws LoanException;
	public void extension (Loan loan) throws LoanException;
	public List<Loan> selectUserNo(Integer userNo) throws LoanException;
	public Integer totalReserv(Integer userNo) throws LoanException;
	public List<Loan> reservloanList(Integer userNo) throws LoanException;
	public List<Loan> selectSearchUserNo(String input) throws LoanException;
	public List<Loan> selectDeadlineOut() throws LoanException;
}
