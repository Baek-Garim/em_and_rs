package com.project.emrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
	
	private int id;
	private String memberid;
	private String memberpw;
	private int point;

}
