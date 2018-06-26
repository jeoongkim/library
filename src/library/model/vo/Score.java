package library.model.vo;

import java.io.Serializable;

public class Score implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer scoreNo;
	private Integer bookScore;
	private String isbn;
	private Integer userNo;
	private Book book;
	private Users users;
	
	public Score() {}

	public Score(Integer scoreNo, Integer bookScore, String isbn, Integer userNo) {
		super();
		this.scoreNo = scoreNo;
		this.bookScore = bookScore;
		this.isbn = isbn;
		this.userNo = userNo;
	}

	public Integer getScoreNo() {
		return scoreNo;
	}

	public void setScoreNo(Integer scoreNo) {
		this.scoreNo = scoreNo;
	}

	public Integer getBookScore() {
		return bookScore;
	}

	public void setBookScore(Integer bookScore) {
		this.bookScore = bookScore;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((bookScore == null) ? 0 : bookScore.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((scoreNo == null) ? 0 : scoreNo.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof Score)) return false;
		Score other = (Score) obj;
		if(this.bookScore.equals(other.bookScore)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [scoreNo=");
		builder.append(scoreNo);
		builder.append(", bookScore=");
		builder.append(bookScore);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", book=");
		builder.append(book);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

	
	
		
}
