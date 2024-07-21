package com.project.emrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolDTO {
	
	private Integer tool_id;
	private String tool_code;
	private String tool_name;
	private String tool_image;
	private String tool_comment;
	private Integer category_id;
	private Character visibility;	// 'N'(삭제됨) 'Y'(삭제 안됨)
	
	// 화면 표시용	
	private String category_name;
	private Integer totalReservationNum;	// 총 예약자 수
	
	
} // tool
