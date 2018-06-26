package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Volunteer implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer volunNo;
	private String title;
	private String description;
	private Date regDate;
	private Integer userNo;
	private String password;
	private Date applyDate;
	
	public Volunteer() {}
	public Volunteer(Integer volunNo, String title, String description, Date regDate, Integer userNo, 
			String password, Date applyDate) {
		super();
		this.volunNo = volunNo;
		this.title = title;
		this.description = description;
		this.regDate = regDate;
		this.userNo = userNo;
		this.password = password;
		this.applyDate = applyDate;
	}
	public Integer getVolunNo() {
		return volunNo;
	}
	public void setVolunNo(Integer volunNo) {
		this.volunNo = volunNo;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((volunNo == null) ? 0 : volunNo.hashCode());
		result = prime * result + ((applyDate == null) ? 0 : applyDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true; 
		}
		if (!(obj instanceof Volunteer)) { 
			return false;
		}
		Volunteer other = (Volunteer) obj;
		if(this.volunNo.equals(other.volunNo)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Volunteer [volunNo=");
		builder.append(volunNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", password=");
		builder.append(password);
		builder.append(", applyDate=");
		builder.append(applyDate);
		builder.append("]");
		return builder.toString();
	}
}
