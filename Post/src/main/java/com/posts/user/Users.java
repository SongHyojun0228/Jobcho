package com.posts.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {
	@Id
	private String id;
	
	@Column
	private String pw;
	
	@Column
	private String username;
}
