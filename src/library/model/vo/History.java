package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class History implements Serializable			//빌렸던 책들에 대한 기록들 
{
	private static final long serialVersionUID = 1L;
	private Integer hisNo;		// pkey  자동증가
	private Integer bookNo;		// 빌린책들의 책 등록번호
	private Integer userNo;		// 빌린 사용자
	private Date loanDate;		// 대출일
	private Date returnDate;	// 반납일
	private Users users;		// 참조
	private BookList bookList;	// 참조
	
	
	public History() {}


	public History(Integer hisNo, Integer bookNo, Integer userNo, Date loanDate, Date returnDate) {
		super();
		this.hisNo = hisNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}


	public Integer getHisNo() {
		return hisNo;
	}


	public void setHisNo(Integer hisNo) {
		this.hisNo = hisNo;
	}


	public Integer getBookNo() {
		return bookNo;
	}


	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}


	public Integer getUserNo() {
		return userNo;
	}


	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}


	public Date getLoanDate() {
		return loanDate;
	}


	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookList == null) ? 0 : bookList.hashCode());
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((hisNo == null) ? 0 : hisNo.hashCode());
		result = prime * result + ((loanDate == null) ? 0 : loanDate.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof History)) return false;
		History other = (History) obj;
		if(this.hisNo.equals(other.hisNo)) return true;
		return false;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("History [hisNo=");
		builder.append(hisNo);
		builder.append(", bookNo=");
		builder.append(bookNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", loanDate=");
		builder.append(loanDate);
		builder.append(", returnDate=");
		builder.append(returnDate);
		builder.append(", users=");
		builder.append(users);
		builder.append(", bookList=");
		builder.append(bookList);
		builder.append("]");
		return builder.toString();
	}

	
}
