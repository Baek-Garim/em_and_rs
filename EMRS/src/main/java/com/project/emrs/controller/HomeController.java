package com.project.emrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.emrs.service.HomeService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		log.debug("메인 화면");
		
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		
		return "home/index";
	}
	
} // controller
