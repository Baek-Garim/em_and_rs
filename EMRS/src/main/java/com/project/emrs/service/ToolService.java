package com.project.emrs.service;

import java.util.ArrayList;
import java.util.List;

import com.project.emrs.dto.ToolCategoryDTO;
import com.project.emrs.dto.ToolDTO;

public interface ToolService {

	ArrayList<ToolDTO> toolList();

	ToolDTO toolDetail(int tool_id);

	ArrayList<ToolDTO> sortCategory(int category);

	String selectCate(int category_id);

	// 전체 장비 가져옴
	ArrayList<ToolDTO> getAllTool();

	// 전체 카테고리 가져옴
	ArrayList<ToolCategoryDTO> getAllCategory();

	// 새로운 장비 추가
	void insertTool(ToolDTO toolDTO);

	// 장비 코드 중복 확인
	int toolCodeCheck(String code);

	// 장비 수정
	void updateTool(ToolDTO toolDTO);

	// 장비 삭제
	void deleteTool(Integer tool_id);

	// 어드민 장비 관리 필터
	List<ToolDTO> getFilterData(String category_id, String tool_code, String visibility);

	void reviveTool(Integer tool_id);


	
} // service
