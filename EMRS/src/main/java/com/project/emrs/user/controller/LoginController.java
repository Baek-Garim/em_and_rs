package com.project.emrs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.emrs.user.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login/login";
	}
	

	@GetMapping("/signup")
	public String showSignupPage() {
		return "login/sign_up";
	}

	
	
}
