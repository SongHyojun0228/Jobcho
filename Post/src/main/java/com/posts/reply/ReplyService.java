package com.posts.reply;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.posts.post.Posts;
import com.posts.user.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	
	public void create(Posts post, String content, Users author) {
		Replies reply = new Replies();
		reply.setContent(content);
		reply.setPost(post);
		reply.setAuthor(author);
		reply.setCreatedTime(LocalDateTime.now());
		this.replyRepository.save(reply);
	}

	
}
