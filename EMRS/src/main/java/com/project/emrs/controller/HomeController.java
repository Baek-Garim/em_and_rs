package com.project.emrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.emrs.dto.TestDTO;
import com.project.emrs.service.HomeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping("/")
	public String home(Model model) {
		TestDTO test = homeService.test();
		model.addAttribute("test", test);
		
		return "home/home";
	}
	
} // controller
