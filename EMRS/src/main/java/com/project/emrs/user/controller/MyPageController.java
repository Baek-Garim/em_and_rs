package com.project.emrs.user.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.emrs.dto.ToolDTO;
import com.project.emrs.user.service.MyPageService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("myPage")
public class MyPageController {
	
	@Autowired
	MyPageService myPageService;
	
	@GetMapping("/updateUser")
	public String myPageUpadateUser(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		
		model.addAttribute("barType", "updateUser");
		
		return "myPage/myPage_updateUser";
	}
	
	@GetMapping("/rental")
	public String myPageRental(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		
		model.addAttribute("barType", "rental");
		
		return "myPage/myPage_rental";
	}

	@GetMapping("/reserve")
	public String myPageReserve(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));		
		
		model.addAttribute("barType", "user");
		return "myPage/myPage_reserve";
	}
	
} // controller
