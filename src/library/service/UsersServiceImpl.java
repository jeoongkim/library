package library.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import library.dao.AuthorityDao;
import library.dao.UsersDao;
import library.model.UsersException;
import library.model.vo.Authority;
import library.model.vo.AuthorityId;
import library.model.vo.Reservation;
import library.model.vo.Users;

@Service
public class UsersServiceImpl implements UsersService 
{
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<Users> list() throws UsersException 
	{
		return usersDao.selectAll();
	}
	@Override
	public Users detail(Integer userNo) throws UsersException 
	{
		return usersDao.select(userNo);
	}
	@Override
	public void add(Users users) throws UsersException 
	{
		String encode = passwordEncoder.encode(users.getPassword());
		users.setPassword(encode);
		Authority auth = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());
		Set<Authority> auths = new HashSet<>();
		auths.add(auth);
		users.setAuthorities(auths);
		usersDao.insert(users);
		Integer userNo = usersDao.selectLastInsertId();
		users.setUserNo(userNo);
		usersDao.insertAuthority(users);
	}
	@Override
	public void remove(Users users) throws UsersException 
	{

		usersDao.delete(users);
	}
	@Override
	public String modify(Users users) throws UsersException 
	{
		Users item=usersDao.select(users.getUserNo());
		String filename = item.getAttachment();
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		usersDao.update(users);

		return filename;
	}
	@Override
	public Users detailByEmail(String email) throws UsersException 
	{
		return usersDao.selectByEmail(email);
	}
	@Override
	public Authority getAuthority(Integer id) throws UsersException 
	{
		return authorityDao.select(id);
	}
	@Override
	public UserDetails getPrincipal() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails) 
		{
			return (UserDetails) principal;
		}
		return null;
	}
	@Override
	public void logout(HttpServletRequest req, HttpServletResponse resp) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
	}
	@Override
	public boolean isPasswordMatched(String oldPassword) throws UsersException 
	{
		String email = this.getPrincipal().getUsername();
		Users users = usersDao.selectByEmail(email);
		return passwordEncoder.matches(oldPassword, users.getPassword());
	}
	@Override
	public String selectName(Integer userNo) throws UsersException {
		
		return usersDao.selectName(userNo);
	}
	@Override
	public void updateMember(Integer userNo) throws UsersException {
		usersDao.updateMember(userNo);
	}
	@Override
	public void updateAuthority(Users users) throws UsersException {
		usersDao.updateAuthority(users);
		
	}
	@Override
	public List<Users> stopDateSelect() throws UsersException {
		
		return usersDao.stopDateSelect();
	}

}
