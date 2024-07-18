package com.project.emrs.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {

	private Integer	user_id;		// 유저ID 인덱스
	private String email;			// 로그인 이메일
	private String user_pw;			// 비밀번호
	private String user_name;		// 이름
	private String phone;			// 전화번호
	private Character isDelete;			// 탈퇴 여부
	private String user_rentable;		// 유저 대여 상태
	private LocalDateTime unavailable_date;		// 대여불가 ~까지
	private String user_grant;		// 권한(일반유저USER | 관리자ADMIN)
	
}
