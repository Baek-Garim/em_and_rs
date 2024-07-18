package com.project.emrs.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservationDTO {

	private Integer reserve_id; 		// 예약 ID
	private String tool_code;			// 장비 코드
	private Integer user_id; 			// 예약자 유저 ID
	private LocalDateTime reserve_date;		// 예약 날짜
	private LocalDateTime deadline_date;	// 예약 가능 후부터 대여가능한 마감일
	private String reserve_state;	// 예약 가능 상태 ('대기중', '대여가능', '대여완료', '예약취소')
	
	
	// 화면 출력용
	private String tool_name;

	private String tool_image;
	private String reserve_date_String;
	private String deadline_date_String;
	private Integer waiting;

	private String user_name;
	private String email;
	private String phone;
	

	

	
}
