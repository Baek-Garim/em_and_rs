package com.project.emrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tool {
	
	int tool_id;
	String tool_code;
	String tool_name;
	String tool_image;
	String tool_comment;
	int category_id;
	String category_name;
	
} // tool
