package com.project.emrs.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.Tool;

@Mapper
public interface ToolDAO {

	ArrayList<Tool> toolList();

	Tool toolDetail(int toolId);

	ArrayList<Tool> sortCategory(int category);

	String selectCate(int category_id);
	
	
} // DAO
