package com.posts.post;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.posts.reply.ReplyForm;
import com.posts.user.UserService;
import com.posts.user.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	private final UserService userService;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Posts> paging = this.postService.getList(page);
		model.addAttribute("paging", paging);

		return "post_list";
	}

	@GetMapping(value = "/detail/{postId}")
	public String detail(Model model, @PathVariable("postId") Integer postId, ReplyForm replyForm) {
		Posts post = this.postService.getPost(postId);
		model.addAttribute("post", post);
		return "post_detail";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String postCreate(PostForm postForm) {
		return "post_form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String postCreate(@Valid PostForm postForm, BindingResult bindingResult
			,Principal principal) {
		if (bindingResult.hasErrors()) {
			return "post_form";
		}

		Users user = this.userService.getUser(principal.getName());
		this.postService.create(postForm.getTitle(), postForm.getContent(), user);
		return "redirect:/post/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{postId}")
	public String postModify(PostForm postForm, @PathVariable("postId") Integer postId, Principal principal) {
		Posts post = this.postService.getPost(postId);
		if(!post.getAuthor().getId().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다."	);
		}
		
		postForm.setTitle(post.getTitle());
		postForm.setContent(post.getContent());
		
		return "post_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{postId}")
	public String postModify(@Valid PostForm postForm, BindingResult bindingResult, 
			Principal principal, @PathVariable("postId") Integer postId) {
		if(bindingResult.hasErrors()) {
			return "post_form";
		}
		
		Posts post = this.postService.getPost(postId);
		if(!post.getAuthor().getId().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		this.postService.modify(post, postForm.getTitle(), postForm.getContent());
		return String.format("redirect:/post/detail/%s", postId);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{postId}")
	public String postDelete(Principal principal, @PathVariable("postId") Integer postId) {
		Posts post = this.postService.getPost(postId);
		if(!post.getAuthor().getId().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		
		this.postService.delete(post);
		
		return "redirect:/";
	}
	
}
