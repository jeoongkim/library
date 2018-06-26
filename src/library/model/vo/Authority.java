package library.model.vo;

import java.io.Serializable;

public class Authority implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;		// 권한 번호
	private String name;	// 권한 이름
	
	public Authority() {}
	
	public Authority(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Authority)) return false; 
		Authority other = (Authority) obj;
		if(this.id.equals(other.id)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		return builder.toString();
	}
	
	
}
