package com.project.emrs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;
import com.project.emrs.dto.ToolCategoryDTO;
import com.project.emrs.service.RentalService;
import com.project.emrs.service.ReservationService;
import com.project.emrs.service.ToolService;
import com.project.emrs.user.dto.UserDTO;
import com.project.emrs.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	RentalService rentalService;
	
	@Autowired
	ReservationService reservService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ToolService toolService;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		log.debug("메인 화면");
		
		//유저 정보, 대여 정보, 예약 정보
		
		// 로그인 되어 있을 때만
		if(session.getAttribute("user_id") != null) {
			ArrayList<ReservationDTO> reservationList = reservService.getUserAllReservation((Integer) session.getAttribute("user_id"));
			ArrayList<RentalDTO> rentalList = rentalService.getUserAllRental((Integer) session.getAttribute("user_id"));
			// 유저 정보
			UserDTO user = userService.getUserInfo((Integer) session.getAttribute("user_id"));
			
			
			model.addAttribute("user", user);
			model.addAttribute("reservationList", reservationList);
			model.addAttribute("rentalList", rentalList);
		}
		ArrayList<ToolCategoryDTO> categoryList = toolService.getAllCategory();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		
		return "home/index";
	}
	
} // controller
