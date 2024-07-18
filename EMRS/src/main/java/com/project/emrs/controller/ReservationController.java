package com.project.emrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.emrs.service.ReservationService;
import com.project.emrs.user.dto.UserDTO;
import com.project.emrs.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reserService;

	@Autowired
	UserService userService;

	
	@GetMapping("/reserve/{tool_code}")
	public String reserveTool(HttpServletRequest request, HttpSession session, @PathVariable("tool_code") String tool_code) {
		Integer user_id = (Integer) session.getAttribute("user_id");

		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		
		if(user_id == null) {
			return "redirect:/login";
		}
		
		// 관리자는 예약할 수 없음
		if(session.getAttribute("user_grant").equals("ADMIN")) {	
	    	request.setAttribute("msg", "관리자는 예약할 수 없습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";			
		}
		
		// 대여 가능 상태여야 함
		UserDTO user = userService.getUserInfo(user_id);
		if(!user.getUser_rentable().equals("대여 가능")) {
	    	request.setAttribute("msg", "대여 가능 상태일 경우에만 예약할 수 있습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";			
		}
		
		// 동일 제품을 대여중이거나 예약중(대기중, 대여가능)이면 안됨.
		Integer duplicateCheck = reserService.duplicateCheck(user_id, tool_code);
		if(duplicateCheck > 0) {
	    	request.setAttribute("msg", "이미 대여중이거나 예약중인 장비는 예약할 수 없습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		// 5건을 넘으면 안됨!
		Integer myReservationNum = reserService.countMyReservation(user_id);
		
		if(myReservationNum >= 5) {
	    	request.setAttribute("msg", "예약은 5건을 초과할 수 없습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		reserService.insertReserve(user_id, tool_code);
		
		
		return "redirect:/";
		
		
	}
	
}
