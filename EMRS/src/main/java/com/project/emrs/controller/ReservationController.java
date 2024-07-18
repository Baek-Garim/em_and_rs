package com.project.emrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.emrs.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reserService;

	@GetMapping("/reserve/{tool_code}")
	public String reserveTool(HttpSession session, @PathVariable("tool_code") String tool_code) {
	
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		reserService.insertReserve((Integer)session.getAttribute("user_id"), tool_code);
		
		
		return "redirect:/";
		
		
	}
	
}
