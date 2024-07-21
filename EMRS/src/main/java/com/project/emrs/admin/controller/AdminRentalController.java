package com.project.emrs.admin.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class AdminRentalController {

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
	


	// 대여 및 반납
	@GetMapping("/rent-return")
	public String showAdminRentalAndReturnPage(Model model) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		
		// 대여 가능 상태인 예약 목록 불러오기
		ArrayList<ReservationDTO> reservationList = reserService.getActivateReserveList();

		// 대여 진행 중인 렌탈 목록 불러오기
		ArrayList<RentalDTO> rentalList = rentalService.getActivateRentalList();
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("rentalList", rentalList);

		model.addAttribute("barType", "rent-return");
		return "admin/admin_rental_return";
	}

	
	// 대여 진행(목록에서 선택함)
	@GetMapping("/rent-return/rent/{reserve_id}")
	public String toolRental(@PathVariable("reserve_id") Integer reserve_id) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
	
		rentalService.insertRental(reserve_id);
		
    	request.setAttribute("msg", "대여 성공");
        request.setAttribute("url", "/"+prevURL);
		
        return "fragments/alert";
	}

	
	// 반납 진행(목록에서 선택함)
	@GetMapping("/rent-return/return/{tool_code}/{rental_id}/{user_id}")
	public String toolReturn(@PathVariable("tool_code") String tool_code , @PathVariable("rental_id") Integer rental_id, 
																						@PathVariable("user_id") Integer user_id) {
		
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		
		rentalService.toolReturn(tool_code, rental_id, user_id);
		
    	request.setAttribute("msg", "반납 성공");
        request.setAttribute("url", "/"+prevURL);
		
        return "fragments/alert";
	}

	
	//	====================== 대여관리 ==

	//대여 관리
	@GetMapping("/rental")
	public String showAdminRentalPage(Model model) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		// 대여 진행 중인 렌탈 목록 불러오기
		List<RentalDTO> rentalList = rentalService.getAllList();
		
		model.addAttribute("rentalList", rentalList);

		model.addAttribute("barType", "rental");
		return "admin/admin_rental";
	}
	
	
	// 필터 & 정렬
	@RequestMapping(value = "/rental/list/filter", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testCheck(@RequestParam(value="listType") String filterType, @RequestParam(value="sortType") String sortType,
										@RequestParam(value="email") String email, @RequestParam(value="tool_code") String tool_code){	
	
		email = email.trim();
		tool_code = tool_code.trim();
		
		List<RentalDTO> rentalList = rentalService.getFilterData(filterType, sortType, email, tool_code);
		
		return new ResponseEntity<List<RentalDTO>>(rentalList, HttpStatus.OK);
	}	
	
	
}
