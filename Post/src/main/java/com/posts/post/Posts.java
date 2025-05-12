package com.posts.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.posts.reply.Replies;
import com.posts.user.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
	@SequenceGenerator(name = "post_seq", sequenceName = "SEQ_POST_ID", allocationSize = 1)
	private Integer postId;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Replies> replyList;
	
	@Column(name = "created_time", insertable = false)
	private LocalDateTime createdTime;
	
	@ManyToOne
	@JoinColumn(name = "author") // 이걸 설정하지 않으면, 변수명 + _id를 붙은 칼럼을 찾음  
	private Users author;
	
	private LocalDateTime modifyTime;
	
	@ManyToMany
	Set<Users> voter;
}
