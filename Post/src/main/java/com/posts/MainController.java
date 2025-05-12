package com.posts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// 'localhost:8080/'로 들어갔을 때 'localhost:8080/post/list'로 이동
	@GetMapping("/")
	public String root() {
		return "redirect:/post/list";
	}

}
