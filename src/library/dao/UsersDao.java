package library.dao;

import java.util.List;

import library.model.UsersException;
import library.model.vo.Users;

public interface UsersDao 
{
	public List<Users> selectAll() throws UsersException;				// 모든 회원정보 가져오기 
	public Users select(Integer userNo) throws UsersException;		// 유저 정보 가져오기
	public void insert(Users users) throws UsersException;			// 회원정보 추가
	public void delete(Users users) throws UsersException;			// 회원정보 삭제
	public void update(Users users) throws UsersException;			// 회원정보 수정
	public Users selectByEmail(String email) throws UsersException;	// 이메일로 회원정보 가져오기
	public void insertAuthority(Users users);	// users_authority 테이블에 정보를 입력하기
	public Integer selectLastInsertId() throws UsersException;
	public String selectName(Integer userNo) throws UsersException;		//유저번호로 유저 이름 가져오기
	public void updateMember(Integer userNo) throws UsersException;
	public void updateAuthority(Users users) throws UsersException;
	public List<Users> stopDateSelect() throws UsersException;
	
}
