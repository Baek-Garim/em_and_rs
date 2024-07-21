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
import com.project.emrs.dto.ToolCategoryDTO;
import com.project.emrs.dto.ToolDTO;
import com.project.emrs.service.RentalService;
import com.project.emrs.service.ReservationService;
import com.project.emrs.service.ToolService;
import com.project.emrs.user.service.MyPageService;
import com.project.emrs.user.service.UserService;

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
	
	@Autowired
	ToolService toolService;	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/updateUser")
	public String myPageUpadateUser(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}

		// 카테고리		
		ArrayList<ToolCategoryDTO> categoryList = toolService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		
		UserDTO user = myPageService.selectUser((Integer)session.getAttribute("user_id"));
		model.addAttribute("user", user);
		
		model.addAttribute("barType", "updateUser");
		
		return "myPage/myPage_updateUser";
	}
	
	@GetMapping("/rental")
	public String myPageRental(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		ArrayList<RentalDTO> rentalList = rentalService.rentalList((Integer)session.getAttribute("user_id"));
		
		// 카테고리		
		ArrayList<ToolCategoryDTO> categoryList = toolService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		
		model.addAttribute("rentalList", rentalList);
		
		model.addAttribute("barType", "rental");
		
		return "myPage/myPage_rental";
	}

	@GetMapping("/reserve")
	public String myPageReserve(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
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
		// 카테고리		
		ArrayList<ToolCategoryDTO> categoryList = toolService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		
		model.addAttribute("reservationList", reservationList);
		
		model.addAttribute("barType", "reserve");
		return "myPage/myPage_reserve";
	}
	
	@GetMapping("/updateUser_checkForm")
	public String checkForm(Model model, HttpSession session) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		return "myPage/myPage_updateUser_checkForm";
	}
	
	@PostMapping("/updateUser_checkForm")
	public String check(Model model, @RequestParam("user_pw") String user_pw, HttpSession session, HttpServletRequest request) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		UserDTO user = myPageService.selectUser((Integer)session.getAttribute("user_id"));
		model.addAttribute("user", user);
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		if (!user.getUser_pw().equals(user_pw)) {
			request.setAttribute("msg", "비밀번호가 다릅니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		return "myPage/myPage_updateUser_form";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute UserDTO user, HttpSession session, Model model) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		user.setUser_id((Integer)session.getAttribute("user_id"));
		userService.updateUser(user);

		return "redirect:/myPage/updateUser";
	}
	
	@GetMapping("/rental_renew{rental_id}")
	public String rental_renew(HttpServletRequest request, HttpSession session, @PathVariable("rental_id") Integer rental_id, Model model) {
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		
		Integer user_id = (Integer) session.getAttribute("user_id");
		
		if(user_id == null) {
			return "redirect:/login";
		}
		
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		RentalDTO rental = rentalService.selectRental(rental_id);
		if (rental.getRental_state().equals("반납")) {
			request.setAttribute("msg", "이미 반납되었습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		if (rental.getRenew() >= 1) {
			request.setAttribute("msg", "연장 횟수를 초과했습니다.");
	        request.setAttribute("url", "/"+prevURL);
	        return "fragments/alert";
		}
		
		rentalService.rentalRenew(rental_id);
		
		return "redirect:/myPage/rental";
		
	}
	
} // controller





