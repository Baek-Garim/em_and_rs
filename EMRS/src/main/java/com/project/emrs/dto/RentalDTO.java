package com.project.emrs.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDTO {

	private Integer rental_id; 			// 대여 ID
	private Integer user_id;			// 대여자 유저 ID
	private String tool_code; 			// 장비 코드
	private LocalDateTime rental_date;		// 예약 날짜
	private LocalDateTime return_date;		// 예약 가능 후부터 대여가능한 마감일
	private Integer renew;					// 연장 횟수 
	private String rental_state;			// 대여 상태 (정상, 연체, 반납)
	
	// 화면 출력용
	private String tool_name;
	private Integer tool_id;
	private String tool_image;
	private String rental_date_String;
	private String return_date_String;
	
}
