package com.project.emrs.user.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.emrs.user.dto.UserDTO;
import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;
import com.project.emrs.dto.ToolDTO;
import com.project.emrs.service.RentalService;
import com.project.emrs.service.ReservationService;
import com.project.emrs.user.service.MyPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("myPage")
public class MyPageController {
	
	@Autowired
	MyPageService myPageService;
	
	@Autowired
	RentalService rentalService;
	
	@Autowired
	ReservationService reservationService;
	
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
		ArrayList<RentalDTO> rentalList = rentalService.rentalList((Integer)session.getAttribute("user_id"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
		for(RentalDTO i: rentalList) {
			i.setRental_date_String(i.getRental_date().format(formatter));
			i.setReturn_date_String(i.getReturn_date().format(formatter));
        }
		
		model.addAttribute("rentalList", rentalList);
		
		model.addAttribute("barType", "rental");
		
		return "myPage/myPage_rental";
	}

	@GetMapping("/reserve")
	public String myPageReserve(Model model, HttpSession session) {
		ArrayList<ReservationDTO> reservationList = reservationService.reservationList((Integer)session.getAttribute("user_id"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
		for(ReservationDTO i: reservationList) {
			i.setReserve_date_String(i.getReserve_date().format(formatter));
			if (i.getDeadline_date() != null) {
	            i.setDeadline_date_String(i.getDeadline_date().format(formatter));
	        } else {
	            i.setDeadline_date_String(" - ");
	        }
			i.setWaiting(reservationService.countMyReservation((Integer)session.getAttribute("user_id")));
        }
		model.addAttribute("reservationList", reservationList);
		
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
	
	@GetMapping("/rental_renew{rental_id}")
	public String rental_renew(HttpServletRequest request, HttpSession session, @PathVariable("rental_id") Integer rental_id) {
		Integer user_id = (Integer) session.getAttribute("user_id");
		
		if(user_id == null) {
			return "redirect:/login";
		}
		
		rentalService.rentalRenew(rental_id);
		
		return "redirect:/myPage/rental";
		
	}
	
} // controller





