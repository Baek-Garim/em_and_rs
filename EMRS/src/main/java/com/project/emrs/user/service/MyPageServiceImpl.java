package com.project.emrs.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.user.dao.MyPageDAO;
import com.project.emrs.user.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	MyPageDAO myPageDAO;

	@Override
	public UserDTO selectUser(Integer user_id) {
		UserDTO user = myPageDAO.selectUser(user_id);
		return user;
	}

	
	
} // impl
