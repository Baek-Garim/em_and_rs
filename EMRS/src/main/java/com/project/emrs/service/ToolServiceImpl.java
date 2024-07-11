package com.project.emrs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.ToolDAO;
import com.project.emrs.dto.Tool;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ToolServiceImpl implements ToolService{
	
	@Autowired
	ToolDAO toolDAO;

	@Override
	public ArrayList<Tool> toolList() {
		ArrayList<Tool> toolList = toolDAO.toolList();
		
		return toolList;
	}

	@Override
	public Tool toolDetail(int toolId) {
		Tool tool = toolDAO.toolDetail(toolId);
		
		return tool;
	}

	@Override
	public ArrayList<Tool> sortCategory(int category) {
		ArrayList<Tool> sortToolList = toolDAO.sortCategory(category);
		return sortToolList;
	}
	
	
} // impl
