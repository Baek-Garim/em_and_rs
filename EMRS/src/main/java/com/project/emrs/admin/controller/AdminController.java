package com.project.emrs.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.emrs.admin.service.AdminService;


@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/tool")
	public String showAdminToolPage(Model model) {
		
		model.addAttribute("barType", "tool");
		return "admin/admin_tool";
	}
	
	@GetMapping("/tool/form")
	public String showAdminToolFormPage(Model model) {
		model.addAttribute("barType", "tool");
		return "admin/admin_tool_form";
	}


	@GetMapping("/rental")
	public String showAdminRentalPage(Model model) {
		
		model.addAttribute("barType", "rental");
		return "admin/admin_rental";
	}

	@GetMapping("/user")
	public String showAdminUserPage(Model model) {
		
		model.addAttribute("barType", "user");
		return "admin/admin_user";
	}

	
	
}
