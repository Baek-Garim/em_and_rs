package com.project.emrs.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.emrs.user.dto.UserDTO;

@Mapper
public interface UserDAO {

	int emailCheck(String email);

	int phoneCheck(String phone);

	void insertUser(UserDTO user);

	UserDTO login(UserDTO user);

	UserDTO getUserInfo(Integer user_id);

	void setOverdue(UserDTO user);

}
