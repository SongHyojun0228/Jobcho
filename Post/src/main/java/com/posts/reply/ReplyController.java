package com.posts.reply;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.posts.post.PostService;
import com.posts.post.Posts;
import com.posts.user.UserService;
import com.posts.user.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {

	private final PostService postService;
	private final ReplyService replySerive;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{postId}")
	public String createReply(Model model, @PathVariable("postId") Integer postId, 
			@Valid ReplyForm replyForm, BindingResult bindingResult, Principal principal) {
		
		Posts post = this.postService.getPost(postId);
		Users user = this.userService.getUser(principal.getName());	
		if(bindingResult.hasErrors()) {
			model.addAttribute("post", post);
			return "post_detail";
		}
		
		this.replySerive.create(post, replyForm.getContent(), user);
		return String.format("redirect:/post/detail/%s", postId);
	}
}
