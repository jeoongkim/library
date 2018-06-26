package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class BookApply implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer applyNo;		// 신청 번호
	private String title;			// 제목
	private String author;			// 작가
	private String publisher;		// 출판사
	private Integer userNo;			// 신청한 회원 번호
	private boolean warehousing;	// 입고여부 default 0 -> false 입고안됨
	private Date applyDate;			// 신청날짜
	private String isbn;			// 책 고유번호
	private Users users;
	private Book book;
	
	public BookApply() {}

	public BookApply(Integer applyNo, String title, String author, String publisher, Integer userNo,
			boolean warehousing, Date applyDate, Users users, String isbn) {
		super();
		this.applyNo = applyNo;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.userNo = userNo;
		this.warehousing = warehousing;
		this.applyDate = applyDate;
		this.users = users;
		this.isbn = isbn;
	}

	public Integer getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public boolean isWarehousing() {
		return warehousing;
	}

	public void setWarehousing(boolean warehousing) {
		this.warehousing = warehousing;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
		result = prime * result + ((applyNo == null) ? 0 : applyNo.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		result = prime * result + ((applyDate == null) ? 0 : applyDate.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + (warehousing ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true; 
		}
		if (!(obj instanceof BookApply)) { 
			return false;
		}
		BookApply other = (BookApply) obj;
		if(this.applyNo.equals(other.applyNo)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookApply [applyNo=");
		builder.append(applyNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", publisher=");
		builder.append(publisher);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", warehousing=");
		builder.append(warehousing);
		builder.append(", users=");
		builder.append(users);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", applyDate=");
		builder.append(applyDate);
		builder.append("]");
		return builder.toString();
	}
	
	

}
