package com.posts.user;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.posts.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Users create(String id, String pw, String username) {
		if (userRepository.existsById(id)) {
			throw new DataIntegrityViolationException("이미 존재하는 ID");
		}

		Users user = new Users();
		user.setId(id);
		user.setPw(passwordEncoder.encode(pw));
		user.setUsername(username);

		return userRepository.save(user);
	}

	public Users getUser(String id) {
		Optional<Users> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new DataNotFoundException("user not found");
		}
	}

}
