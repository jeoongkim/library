package library.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Users implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer userNo;				// 자동증가 유일값
	private String email;				// 가입시 e-mail uniq
	private String password;			// 사용자 비밀번호
	private String name;				// 사용자 이름
	private String phoneNum;			// 사용자 전화번호
	private String attachment;			// 프로필 사진 파일명
	private Date stopDate;				// 권한 정지 기간
	private Set<Authority> authorities;		// 사용자 권한
	
	public Users() {}
	
	public Users(Integer userNo, String email, String password, String name, String phoneNum, String attachment,
			Date stopDate) {
		super();
		this.userNo = userNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNum = phoneNum;
		this.attachment = attachment;
		this.stopDate = stopDate;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + ((stopDate == null) ? 0 : stopDate.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof Users)) return false;
		Users other = (Users) obj;
		if(this.userNo.equals(other.userNo)) return true;
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Users [userNo=");
		builder.append(userNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phoneNum=");
		builder.append(phoneNum);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", stopDate=");
		builder.append(stopDate);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append("]");
		return builder.toString();
	}
	
}
