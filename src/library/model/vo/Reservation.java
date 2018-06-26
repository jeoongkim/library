package library.model.vo;

import java.io.Serializable;

public class Reservation implements Serializable	//예약 
{
	private static final long serialVersionUID = 1L;
	private Integer reserNo;	//예약번호 자동증가
	private String isbn;	// 책 고유번호
	private Integer userNo;	//유저의 등록번호
	private Users users;	// 유저를 참조
	private Book book;		// 책 참조
	
	public Reservation() {}

	public Reservation(Integer reserNo, String isbn, Integer userNo, Users users, Book book) {
		super();
		this.reserNo = reserNo;
		this.isbn = isbn;
		this.userNo = userNo;
		this.users = users;
		this.book = book;
	}

	public Integer getReserNo() {
		return reserNo;
	}

	public void setReserNo(Integer reserNo) {
		this.reserNo = reserNo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((reserNo == null) ? 0 : reserNo.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		Reservation other = (Reservation) obj;
		if(!(obj instanceof Reservation)) return false;
		if(this.reserNo.equals(other.reserNo)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservation [reserNo=");
		builder.append(reserNo);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", users=");
		builder.append(users);
		builder.append(", book=");
		builder.append(book);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
