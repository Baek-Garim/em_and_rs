package com.project.emrs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.ToolCategoryDTO;
import com.project.emrs.dto.ToolDTO;

@Mapper
public interface ToolDAO {

	ArrayList<ToolDTO> toolList();

	ToolDTO toolDetail(int tool_id);

	ArrayList<ToolDTO> sortCategory(int category);

	String selectCate(int category_id);

	ArrayList<ToolDTO> getAllTool();
	
	ArrayList<ToolCategoryDTO> getAllCategory();

	void insertTool(ToolDTO toolDTO);

	int toolCodeCheck(String code);

	void updateTool(ToolDTO toolDTO);

	void deleteTool(Integer tool_id);

	List<ToolDTO> getFilterData(Map<String, String> filterMap);

	void reviveTool(Integer tool_id);


	
	
} // DAO
