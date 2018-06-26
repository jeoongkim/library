package library.model.vo;

import java.io.Serializable;
import java.util.Date;

public class ReadingRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer readingRoomNo;
	private Integer userNo;
	private Integer roomNo;
	private Integer seatNo;
	private boolean reservation;
	private Date time;
	private Users users;
	
	public ReadingRoom() {}
	
	public ReadingRoom(Integer readingRoomNo, Integer userNo, Integer roomNo, Integer seatNo, boolean reservation,
			Date time) {
		this.readingRoomNo = readingRoomNo;
		this.userNo = userNo;
		this.roomNo = roomNo;
		this.seatNo = seatNo;
		this.reservation = reservation;
		this.time = time;
	}

	public Integer getReadingRoomNo() {
		return readingRoomNo;
	}

	public void setReadingRoomNo(Integer readingRoomNo) {
		this.readingRoomNo = readingRoomNo;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isReservation() {
		return reservation;
	}

	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		result = prime * result + ((readingRoomNo == null) ? 0 : readingRoomNo.hashCode());
		result = prime * result + (reservation ? 1231 : 1237);
		result = prime * result + ((roomNo == null) ? 0 : roomNo.hashCode());
		result = prime * result + ((seatNo == null) ? 0 : seatNo.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof ReadingRoom)) return false;
		ReadingRoom other = (ReadingRoom) obj;
		if(this.readingRoomNo.equals(other.readingRoomNo)) return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReadingRoom [readingRoomNo=");
		builder.append(readingRoomNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", roomNo=");
		builder.append(roomNo);
		builder.append(", seatNo=");
		builder.append(seatNo);
		builder.append(", reservation=");
		builder.append(reservation);
		builder.append(", time=");
		builder.append(time);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
