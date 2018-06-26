package library.model.vo;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private Integer isbnNo;     	 		  // 바코드 번호 유일값
	private String isbn;     	 		  // 바코드 번호 유일값
	private String title;       		 // 책 제목
	private String author;     			 // 저자
	private String publisher;   	   	// 출판사
	private String attachment;    		  //첨부 파일명
	private List<Score> score;				// 별점
	private String groupName;			// 도서분류 (O : 오디오 / A : 어른용 / C : 아동용) 
	
	public Book() {}

	public Book(Integer isbnNo, String isbn, String title, String author, String publisher,
			String attachment, List<Score> score, String groupName) {
		super();
		this.isbnNo = isbnNo;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.attachment = attachment;
		this.score = score;
		this.groupName = groupName;
	}

	public Integer getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(Integer isbnNo) {
		this.isbnNo = isbnNo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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


	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((isbnNo == null) ? 0 : isbnNo.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof Book)) return false;
		Book other = (Book) obj;
		if(this.isbn.equals(other.isbn)) return true;
		return false;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [isbnNo=");
		builder.append(isbnNo);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", publisher=");
		builder.append(publisher);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", score=");
		builder.append(score);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
