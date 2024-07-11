package com.project.emrs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.vo.Test;

@Mapper
public interface HomeDAO {

	Test test();
	
} // DAO
