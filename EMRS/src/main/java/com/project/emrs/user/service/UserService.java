package com.project.emrs.user.service;

import com.project.emrs.user.dto.UserDTO;

public interface UserService {

	int emailCheck(String email);

	int phoneCheck(String phone);

	void insertUser(UserDTO user);

	UserDTO login(UserDTO user);

	UserDTO getUserInfo(Integer user_id);

}
