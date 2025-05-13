package com.jobcho.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Users create(String email, String password, String username) {
		Users user = new Users();
		user.setUserName(username);
		user.setUserEmail(email);
		user.setUserPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
	
	public Optional<Users> getUser(String userEmail) {
		Optional<Users> user = this.userRepository.findByUserEmail(userEmail);

		return user;
	}

}
