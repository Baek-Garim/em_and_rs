package com.project.emrs.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.user.dao.UserDAO;
import com.project.emrs.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;

	@Override
	public int emailCheck(String email) {
		return userDAO.emailCheck(email);
	}

	@Override
	public int phoneCheck(String phone) {
		return userDAO.phoneCheck(phone);
	}

	@Override
	public void insertUser(UserDTO user) {
		user.setUser_grant("USER");
		user.setUser_rentable("대여 가능");
		userDAO.insertUser(user);
	}

	@Override
	public UserDTO login(UserDTO user) {
		return userDAO.login(user);
	}

	@Override
	public UserDTO getUserInfo(Integer user_id) {
		return userDAO.getUserInfo(user_id);
	}
	
}
