package com.project.emrs.user.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.emrs.user.dto.UserDTO;
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
		
		UserDTO user = myPageService.selectUser((Integer)session.getAttribute("user_id"));
		model.addAttribute("user", user);
		
		model.addAttribute("barType", "updateUser");
		
		return "myPage/myPage_updateUser";
	}
	
	@GetMapping("/rental")
	public String myPageRental(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		model.addAttribute("barType", "rental");
		
		return "myPage/myPage_rental";
	}

	@GetMapping("/reserve")
	public String myPageReserve(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		model.addAttribute("barType", "reserve");
		return "myPage/myPage_reserve";
	}
	
	@GetMapping("/updateUser_checkForm")
	public String checkForm(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		return "myPage/myPage_updateUser_checkForm";
	}
	
	@PostMapping("/updateUser_checkForm")
	public String check(Model model, @RequestParam("user_pw") String user_pw, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		UserDTO user = myPageService.selectUser((Integer)session.getAttribute("user_id"));
		String currentPw = user.getUser_pw();
		if (!currentPw.equals(user_pw)) {
			return "redirect:/myPage/myPage_updateUser";
		}
		
		return "myPage/myPage_updateUser_form";
	}
	
	
} // controller





