package com.project.emrs.service;

import java.util.ArrayList;

import com.project.emrs.dto.ToolDTO;

public interface ToolService {

	ArrayList<ToolDTO> toolList();

	ToolDTO toolDetail(int toolId);

	ArrayList<ToolDTO> sortCategory(int category);

	String selectCate(int category_id);
	
} // service
