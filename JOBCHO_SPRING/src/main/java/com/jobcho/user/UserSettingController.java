package com.jobcho.user;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserSettingController {

	private final UserService userService;

	@GetMapping("/setting")
	public String getUserSetting(Model model, Principal principal) {
		Optional<Users> user = this.userService.getUser(principal.getName());
		model.addAttribute(user);

		return "user/user_setting";
	}
}
