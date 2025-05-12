package com.posts.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@NotEmpty(message = "아이디는 필수항목입니다.")
	private String id;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다")
	private String pw;
	
	@NotEmpty(message = "사용자 ID는 필수 항목입니다")
	private String username;
}
