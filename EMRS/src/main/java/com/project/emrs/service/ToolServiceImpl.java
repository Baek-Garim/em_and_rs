package com.project.emrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.ToolDAO;
import com.project.emrs.dto.ToolCategoryDTO;
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
	public ToolDTO toolDetail(int tool_id) {
		return toolDAO.toolDetail(tool_id);
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
	
	// 모든 장비 가져옴
	@Override
	public ArrayList<ToolDTO> getAllTool() {
		return toolDAO.getAllTool();
	}

	// 모든 카테고리 불러오기
	@Override
	public ArrayList<ToolCategoryDTO> getAllCategory() {
		return toolDAO.getAllCategory();
	}

	// 새로운 장비 추가
	@Override
	public void insertTool(ToolDTO toolDTO) {
		toolDAO.insertTool(toolDTO);
	}

	// 장비 코드 중복 검사
	@Override
	public int toolCodeCheck(String code) {
		return toolDAO.toolCodeCheck(code);
	}

	// 장비 수정 진행
	@Override
	public void updateTool(ToolDTO toolDTO) {		
		toolDAO.updateTool(toolDTO);
	}

	@Override
	public void deleteTool(Integer tool_id) {
		toolDAO.deleteTool(tool_id);
	}

	@Override
	public List<ToolDTO> getFilterData(String category_id, String tool_code) {
		Map<String, String> filterMap = new HashMap<String, String>();		
		filterMap.put("category_id", category_id);		
		filterMap.put("tool_code", tool_code);		
		
		
		System.out.println(filterMap.get("category_id"));
		System.out.println(filterMap.get("category_id"));
		System.out.println(filterMap.get("tool_code"));
		System.out.println(filterMap.get("tool_code"));
		
		return toolDAO.getFilterData(filterMap);
	}


	
	
} // impl
