package com.project.emrs.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.user.dao.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDAO loginDAO;
	
}
