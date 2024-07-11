package com.project.emrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tool {
	
	int id;
	String tool_name;
	String tool_image;
	String tool_comment;
	int tool_category;
	int tool_quantity;
	String rentalable;
	
} // tool
