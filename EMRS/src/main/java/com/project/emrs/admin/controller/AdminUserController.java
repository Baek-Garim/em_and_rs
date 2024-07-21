package com.project.emrs.admin.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.emrs.admin.service.AdminService;
import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;
import com.project.emrs.service.RentalService;
import com.project.emrs.service.ReservationService;
import com.project.emrs.service.ToolService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("admin")
public class AdminUserController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	ToolService toolService;
	
	@Autowired
	ReservationService reserService;
	
	@Autowired
	RentalService rentalService;
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;
	
	public String loginCheck() {		
		// 미로그인 or 어드민이 아닐때
		if(session.getAttribute("user_id") == null) {		
			return "login";
		}

		if(!session.getAttribute("user_grant").equals("ADMIN")) {
			return "";
		}
		
		return "OK";
	}
	


	@GetMapping("/user")
	public String showAdminUserPage(Model model) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
    	request.setAttribute("msg", "미구현 페이지 입니다.");
        request.setAttribute("url", "/"+prevURL);
        return "fragments/alert";			
		
		//model.addAttribute("barType", "user");
		//return "admin/admin_user";
	}

	
}
