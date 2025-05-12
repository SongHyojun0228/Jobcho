package com.posts.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.posts.DataNotFoundException;
import com.posts.user.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;
	
	// paging
	public Page<Posts> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createdTime"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.postRepository.findAll(pageable);
	}

//	public List<Posts> getList() {
//		return this.postRepository.findAll();
//	}

	public Posts getPost(Integer postId) {
		Optional<Posts> post = this.postRepository.findById(postId);
		if (post.isPresent()) {
			return post.get();
		} else {
			throw new DataNotFoundException("post not found");
		}
	}

	public void create(String title, String content, Users user) {
		Posts post = new Posts();
		post.setTitle(title);
		post.setContent(content);
		post.setAuthor(user);
		post.setCreatedTime(LocalDateTime.now());
		this.postRepository.save(post);
	}
	
	public void modify(Posts post, String title, String content) {
		post.setTitle(title);
		post.setContent(content);
		post.setModifyTime(LocalDateTime.now());
		this.postRepository.save(post);
	}
	
	public void delete(Posts post) {
		this.postRepository.delete(post);
	}
}
