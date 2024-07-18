package com.project.emrs.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.user.dto.UserDTO;

@Mapper
public interface MyPageDAO {

	UserDTO selectUser(Integer user_id);

	
	
} // DAO
