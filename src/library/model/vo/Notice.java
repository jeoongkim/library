package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer noticeNo;		// 공지사항 글번호 Primary Key
	private String title;			// 제목
	private String description;		// 내용
	private Date regDate; 			// 등록 시간
	private Integer userNo;			// 작성자 고유번호
	private Users users;			// users 참조
	
	public Notice() {}

	public Notice(Integer noticeNo, String title, String description, Date regDate, Integer userNo, Users users) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.description = description;
		this.regDate = regDate;
		this.userNo = userNo;
		this.users = users;
	}

	public Integer getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(Integer noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((noticeNo == null) ? 0 : noticeNo.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true; 
		}
		if (!(obj instanceof Notice)) { 
			return false;
		}
		Notice other = (Notice) obj;
		if(this.noticeNo.equals(other.noticeNo)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [noticeNo=");
		builder.append(noticeNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
