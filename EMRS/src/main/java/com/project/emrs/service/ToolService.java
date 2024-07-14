package com.project.emrs.service;

import java.util.ArrayList;

import com.project.emrs.dto.Tool;

public interface ToolService {

	ArrayList<Tool> toolList();

	Tool toolDetail(int toolId);

	ArrayList<Tool> sortCategory(int category);

	String selectCate(int category_id);
	
} // service
