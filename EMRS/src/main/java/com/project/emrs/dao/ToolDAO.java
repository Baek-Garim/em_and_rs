package com.project.emrs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.ToolDTO;

@Mapper
public interface ToolDAO {

	ArrayList<ToolDTO> toolList();

	ToolDTO toolDetail(int toolId);

	ArrayList<ToolDTO> sortCategory(int category);

	String selectCate(int category_id);
	
	
} // DAO
