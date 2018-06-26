package library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import library.model.vo.Authority;
import library.model.vo.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService 
{

	@Autowired
	private UsersService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		
		try {
			Users users = service.detailByEmail(email);
			
			if (users == null) 
			{
				throw new UsernameNotFoundException("해당 사용자를 찾지 못했습니다.");
			} 
			if(users != null) {
				String item = users.getAuthorities().toString();
				if (item.equals("[UNUSERS]")) {
					throw new UsernameNotFoundException("탈퇴한 회원.");
				}
			}
			return new User(users.getEmail(),
					users.getPassword(),
					true,
					true,
					true,
					true,
					this.getGrantedAuthorities(users));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		
		return null;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Users users) {
		
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for (Authority item : users.getAuthorities()) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + item.getName()));
		}
		
		System.out.println(users.getEmail() + " 사용자의 권한: " + auths);
		return auths;
	}
}
