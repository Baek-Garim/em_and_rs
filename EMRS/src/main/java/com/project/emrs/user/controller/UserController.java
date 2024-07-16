package com.project.emrs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.emrs.user.dto.UserDTO;
import com.project.emrs.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String showLoginPage(HttpSession session) {
		if (session.getAttribute("user_id") != null) {
			return "redirect:/";
		}
		return "login/login";
	}
	

	@GetMapping("/signup")
	public String showSignupPage(HttpSession session) {
		if (session.getAttribute("user_id") != null) {
			return "redirect:/";
		}
		return "login/sign_up";
	}

	
	// 유저 데이터 insert 처리 
	@PostMapping("/signup/insert")
	public String insertUser(@ModelAttribute UserDTO user) {
		// 유저 데이터 insert
		userService.insertUser(user);

		return "redirect:/login";
	}
	
	
	// 로그인 결과처리
	@PostMapping("/login/result")
	public String signin_result(Model model, @RequestParam("email") String email, 
									@RequestParam("user_pw") String user_pw, HttpSession session) {
		UserDTO user = new UserDTO();

		try {
			user.setEmail(email);
			user.setUser_pw(user_pw);
			
			user = userService.login(user);
			
			if(user != null) {
				// 로그인 성공
				// 세션에 값 저장
				session.setAttribute("user_id", user.getUser_id());
				session.setAttribute("user_name", user.getUser_name());
				session.setAttribute("user_grant", user.getUser_grant());
				
	
				return "redirect:/";
			}else {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "login/login";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
		
		return "redirect:/login";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션클리어
		return "redirect:/login";
	}

	
	// 이메일 중복 검사
	@RequestMapping(value="/emailCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam(value="email")  String email) {
		String chk = "";
		int result = 0;
		
		result = userService.emailCheck(email);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
	// 폰 중복 검사
	@RequestMapping(value="/phoneCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String phonecheck(@RequestParam(value="phone")  String phone) {
		String chk = "";
		int result = 0;
		
		result = userService.phoneCheck(phone);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
}
