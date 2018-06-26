package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable		//대출 
{
	private static final long serialVersionUID = 1L;
	private Integer loanNo;			// 대출시 자동증가 번호
	private Date reservLoanDate;	// 예약자를 위한 대출 대기기간
	private Integer userNo;			// user의 등록번호
	private Integer bookNo;			// book의 등록번호
	private Date loanDate;			// 대출 일
	private Date deadline;			// 반납 예정일
	private boolean extension;		// 대출 연장 여부(대출 연장하면 T) 
	private Users users;			// users를 참조
	private BookList bookList;		// booklist를 참조
	
	public Loan() {}

	public Loan(Integer loanNo, Date reservLoanDate, Integer userNo, Integer bookNo, Date loanDate, Date deadline, boolean extension) {
		super();
		this.loanNo = loanNo;
		this.reservLoanDate = reservLoanDate;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.loanDate = loanDate;
		this.deadline = deadline;
		this.extension = extension;
	}

	public Integer getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(Integer loanNo) {
		this.loanNo = loanNo;
	}

	public Date getReservLoanDate() {
		return reservLoanDate;
	}

	public void setReservLoanDate(Date reservLoanDate) {
		this.reservLoanDate = reservLoanDate;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Integer getBookNo() {
		return bookNo;
	}

	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public BookList getBookList() {
		return bookList;
	}

	public void setBookList(BookList bookList) {
		this.bookList = bookList;
	}
	

	public boolean isExtension() {
		return extension;
	}

	public void setExtension(boolean extension) {
		this.extension = extension;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookList == null) ? 0 : bookList.hashCode());
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + (extension ? 1231 : 1237);
		result = prime * result + ((loanDate == null) ? 0 : loanDate.hashCode());
		result = prime * result + ((loanNo == null) ? 0 : loanNo.hashCode());
		result = prime * result + ((reservLoanDate == null) ? 0 : reservLoanDate.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof Loan)) return false;
		Loan other = (Loan) obj;
		if(this.loanNo.equals(other.loanNo)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loan [loanNo=");
		builder.append(loanNo);
		builder.append(", reservLoanDate=");
		builder.append(reservLoanDate);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", bookNo=");
		builder.append(bookNo);
		builder.append(", loanDate=");
		builder.append(loanDate);
		builder.append(", deadline=");
		builder.append(deadline);
		builder.append(", users=");
		builder.append(users);
		builder.append(", bookList=");
		builder.append(bookList);
		builder.append(", extension=");
		builder.append(extension);
		builder.append("]");
		return builder.toString();
	}

	
}
