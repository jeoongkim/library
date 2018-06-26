package library.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;

import library.model.UsersException;
import library.model.vo.Authority;
import library.model.vo.Reservation;
import library.model.vo.Users;

public interface UsersService 
{
	public List<Users> list() throws UsersException;	// 모든 유저 정보가져오기
	public Users detail(Integer userNo) throws UsersException;				// 회원의 상세정보보기
	public void add(Users users) throws UsersException;						// 회원정보 추가
	public void remove(Users user) throws UsersException;	//회원정보 삭제
	public String modify(Users users) throws UsersException;					// 회원정보 수정
	public Users detailByEmail(String email) throws UsersException;			// 회원 상세정보보기
	public Authority getAuthority(Integer id) throws UsersException;			// 회원 권한 가져오기
	public UserDetails getPrincipal();					//  Principal 객체 가져오기
	public void logout(HttpServletRequest req, HttpServletResponse resp);	// 로그아웃
	public boolean isPasswordMatched(String oldPassword) throws UsersException; // 비밀번호 일치 여부 확인하는 메소드
	public String selectName(Integer userNo) throws UsersException;
	public void updateMember(Integer userNo) throws UsersException;
	public void updateAuthority(Users users) throws UsersException;
	public List<Users> stopDateSelect() throws UsersException;
	
}