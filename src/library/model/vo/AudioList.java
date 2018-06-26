package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class AudioList  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer audioNo;				// 책 등록번호(고유값) 자동증가
	private Date wearingDay;			// 도서관 입고일
	private Date publicationDay;		// 책 출판일
	private String isbn;				// book에서의 isbn 값
	private String file;				// file 경로
	private Book book;					// book 참조
	
	public AudioList() {}

	public AudioList(Integer audioNo, Date wearingDay, Date publicationDay, String isbn, String file, Book book) {
		super();
		this.audioNo = audioNo;
		this.wearingDay = wearingDay;
		this.publicationDay = publicationDay;
		this.isbn = isbn;
		this.file = file;
		this.book = book;
	}

	public Integer getAudioNo() {
		return audioNo;
	}

	public void setAudioNo(Integer audioNo) {
		this.audioNo = audioNo;
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
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((audioNo == null) ? 0 : audioNo.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((publicationDay == null) ? 0 : publicationDay.hashCode());
		result = prime * result + ((wearingDay == null) ? 0 : wearingDay.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof AudioList)) return false;
		AudioList other = (AudioList) obj;
		if(this.audioNo.equals(other.audioNo)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookList [audioNo=");
		builder.append(audioNo);
		builder.append(", wearingDay=");
		builder.append(wearingDay);
		builder.append(", publicationDay=");
		builder.append(publicationDay);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", file=");
		builder.append(file);
		builder.append(", book=");
		builder.append(book);
		builder.append("]");
		return builder.toString();
	}

}
