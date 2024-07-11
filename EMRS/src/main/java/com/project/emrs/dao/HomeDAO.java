package com.project.emrs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.dto.TestDTO;

@Mapper
public interface HomeDAO {

	TestDTO test();
	
} // DAO
