package com.project.emrs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.ToolDAO;
import com.project.emrs.dto.ToolDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ToolServiceImpl implements ToolService{
	
	@Autowired
	ToolDAO toolDAO;

	@Override
	public ArrayList<ToolDTO> toolList() {
		ArrayList<ToolDTO> toolList = toolDAO.toolList();
		
		return toolList;
	}

	@Override
	public ToolDTO toolDetail(int toolId) {
		ToolDTO tool = toolDAO.toolDetail(toolId);
		
		return tool;
	}

	@Override
	public ArrayList<ToolDTO> sortCategory(int category) {
		ArrayList<ToolDTO> sortToolList = toolDAO.sortCategory(category);
		return sortToolList;
	}

	@Override
	public String selectCate(int category_id) {
		String category_name = toolDAO.selectCate(category_id);
		return category_name;
	}
	
	
} // impl
