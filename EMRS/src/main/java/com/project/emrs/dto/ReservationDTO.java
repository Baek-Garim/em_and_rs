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
	private Character reserve_state;	// 예약 가능 상태 (Y(대여 가능), N(대여 불가))
	
	
	// 화면 출력용
	private String tool_name;

	
	
}
