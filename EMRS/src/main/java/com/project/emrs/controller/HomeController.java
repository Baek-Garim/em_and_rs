package com.project.emrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		log.debug("메인 화면");
		
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		
		return "home/index";
	}
	
} // controller
