package com.project.emrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emrs.dao.HomeDAO;
import com.project.emrs.service.HomeService;
import com.project.emrs.vo.Test;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;

	@Override
	public Test test() {
		Test test = homeDAO.test();
		
		return test;
	}
	
} // Impl
