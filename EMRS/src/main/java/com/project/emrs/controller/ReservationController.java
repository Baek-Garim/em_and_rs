package com.project.emrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.emrs.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reserService;

	@GetMapping("/reserve/{tool_code}")
	public String reserveTool(HttpServletRequest request, HttpSession session, @PathVariable("tool_code") String tool_code) {
		Integer user_id = (Integer) session.getAttribute("user_id");
		
		if(user_id == null) {
			return "redirect:/login";
		}
		
		// 동일 제품을 대여중이거나 예약중(대기중, 대여가능)이면 안됨.
		Integer duplicateCheck = reserService.duplicateCheck(user_id, tool_code);
		if(duplicateCheck > 0) {
			String prevURL = request.getHeader("referer").substring(22);
			
	    	request.setAttribute("msg", "이미 대여중이거나 예약중인 장비는 예약할 수 없습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		// 5건을 넘으면 안됨!
		Integer myReservationNum = reserService.countMyReservation(user_id);
		if(myReservationNum >= 5) {
			String prevURL = request.getHeader("referer").substring(22);
			
	    	request.setAttribute("msg", "예약 횟수를 초과할 수 없습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		reserService.insertReserve(user_id, tool_code);
		
		
		return "redirect:/";
		
		
	}
	
}
