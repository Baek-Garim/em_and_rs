package com.project.emrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tool {
	
	private int tool_id;
	private String tool_code;
	private String tool_name;
	private String tool_image;
	private String tool_comment;
	private int category_id;
	private String category_name;
	
} // tool
