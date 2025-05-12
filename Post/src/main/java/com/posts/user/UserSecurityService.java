package com.posts.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		Optional<Users> _user = this.userRepository.findById(id);
				
		// 해당 아이디의 계정 존재 X 
		if(_user.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다."); 
		}
		
		// 해당 아이디의 계정 존재
		Users user = _user.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		String username = user.getUsername();
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(user.getId(), user.getPw(), authorities);
	}

}
