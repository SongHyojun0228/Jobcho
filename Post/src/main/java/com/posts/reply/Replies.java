package com.posts.reply;

import java.time.LocalDateTime;
import java.util.Set;

import com.posts.post.Posts;
import com.posts.user.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Replies {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
	@SequenceGenerator(name = "reply_seq", sequenceName = "SEQ_REPLY_ID", allocationSize = 1)
	private Integer replyId;
	
	@Column
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Posts post;
	
	@Column(name = "created_time", insertable = false)
	private LocalDateTime createdTime;
	
	@ManyToOne
	@JoinColumn(name = "author") // 이걸 설정하지 않으면, 변수명 + _id를 붙은 칼럼을 찾음  
	private Users author;
	
	private LocalDateTime modifyTime;
	
	@ManyToMany
	Set<Users> voter;
}
