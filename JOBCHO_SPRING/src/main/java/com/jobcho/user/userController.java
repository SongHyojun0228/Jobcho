package com.jobcho.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class userController {

	private final UserService userService;

	userController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	// 📌 회원가입 페이지
	@GetMapping("/signup")
	public String getSignup(UserCreateForm userCreateForm) {
		return "user/signup";
	}

	// 📌 회원가입
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/signup";
		}

		if (!userCreateForm.getUserPassword().equals(userCreateForm.getUserCheckPassword())) {
			bindingResult.rejectValue("checkPassword", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다");
			return "user/signup";
		}

		try {
			userService.create(userCreateForm.getUserEmail(), userCreateForm.getUserPassword(),
					userCreateForm.getUserName());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "user/signup";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "user/signup";
		}

		return "redirect:/";
	}

	// 📌 로그인 페이지
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("title", "로그인");
		return "user/login";
	}

}
