package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class BookList  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer bookNo;				// 책 등록번호(고유값) 자동증가
	private Date wearingDay;			// 도서관 입고일
	private Date publicationDay;		// 책 출판일
	private String isbn;				// book에서의 isbn 값
	private boolean whether;			// 대출여부
	private Book book;					// book 참조
	
	public BookList() {}

	public BookList(Integer bookNo, Date wearingDay, Date publicationDay, String isbn, boolean whether, Book book) {
		super();
		this.bookNo = bookNo;
		this.wearingDay = wearingDay;
		this.publicationDay = publicationDay;
		this.isbn = isbn;
		this.whether = whether;
		this.book = book;
	}

	public Integer getBookNo() {
		return bookNo;
	}

	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}

	public Date getWearingDay() {
		return wearingDay;
	}

	public void setWearingDay(Date wearingDay) {
		this.wearingDay = wearingDay;
	}

	public Date getPublicationDay() {
		return publicationDay;
	}

	public void setPublicationDay(Date publicationDay) {
		this.publicationDay = publicationDay;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isWhether() {
		return whether;
	}

	public void setWhether(boolean whether) {
		this.whether = whether;
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
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((publicationDay == null) ? 0 : publicationDay.hashCode());
		result = prime * result + ((wearingDay == null) ? 0 : wearingDay.hashCode());
		result = prime * result + (whether ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof BookList)) return false;
		BookList other = (BookList) obj;
		if(this.bookNo.equals(other.bookNo)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookList [bookNo=");
		builder.append(bookNo);
		builder.append(", wearingDay=");
		builder.append(wearingDay);
		builder.append(", publicationDay=");
		builder.append(publicationDay);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", whether=");
		builder.append(whether);
		builder.append(", book=");
		builder.append(book);
		builder.append("]");
		return builder.toString();
	}


	
	
	
}
