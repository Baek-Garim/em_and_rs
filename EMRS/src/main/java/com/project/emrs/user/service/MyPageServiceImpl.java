package com.project.emrs.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.user.dao.MyPageDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	MyPageDAO myPageDAO;
	
	
} // impl
