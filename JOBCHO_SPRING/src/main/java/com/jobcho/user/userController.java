package com.jobcho.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
	    model.addAttribute("title", "회원가입");
	    return "user/signup";
	}
	
	@GetMapping("/login") 
	public String getLogin() {
		return "user/login";
	}
}
