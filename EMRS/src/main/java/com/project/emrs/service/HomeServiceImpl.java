package com.project.emrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.HomeDAO;
import com.project.emrs.dto.TestDTO;
import com.project.emrs.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;

	@Override
	public TestDTO test() {
		TestDTO test = homeDAO.test();
		
		return test;
	}
	
} // Impl
