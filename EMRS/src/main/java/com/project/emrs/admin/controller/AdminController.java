package com.project.emrs.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.emrs.admin.service.AdminService;
import com.project.emrs.dto.RentalDTO;
import com.project.emrs.dto.ReservationDTO;
import com.project.emrs.dto.ToolCategoryDTO;
import com.project.emrs.dto.ToolDTO;
import com.project.emrs.service.RentalService;
import com.project.emrs.service.ReservationService;
import com.project.emrs.service.ToolService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	ToolService toolService;
	
	@Autowired
	ReservationService reserService;
	
	@Autowired
	RentalService rentalService;
	

	
	@GetMapping("/tool")
	public String showAdminToolPage(Model model) {
		ArrayList<ToolDTO> toolList = toolService.getAllTool();
		
		model.addAttribute("barType", "tool");
		model.addAttribute("toolList", toolList);
		
		return "admin/admin_tool";
	}
	
	@GetMapping("/tool/form")
	public String showAdminToolFormPage(Model model) {
		// 카테고리 정보
		ArrayList<ToolCategoryDTO> category = toolService.getAllCategory();
		
		ToolDTO tool = new ToolDTO();
		model.addAttribute("tool", tool);		
		model.addAttribute("category", category);
		model.addAttribute("barType", "tool");
		model.addAttribute("action", "/admin/tool/form/insert");
		return "admin/admin_tool_form";
	}

	// 대여 및 반납
	@GetMapping("/rent-return")
	public String showAdminRentalAndReturnPage(Model model) {
		
		// 대여 가능 상태인 예약 목록 불러오기
		ArrayList<ReservationDTO> reservationList = reserService.getActivateReserveList();

		// 대여 진행 중인 렌탈 목록 불러오기
		ArrayList<RentalDTO> rentalList = rentalService.getActivateRentalList();
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("rentalList", rentalList);

		model.addAttribute("barType", "rent-return");
		return "admin/admin_rental_return";
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
	
	
	// 장비 코드 중복 검사
	@RequestMapping(value="/toolCodeCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam(value="code")  String code, @RequestParam(value="originToolCode") String originToolCode) {
		String chk = "";
		int result = 0;
		
		// 수정 상태일때 저장되어있던 코드와 다시 쓴 코드가 같으면 중복 아님
		if(code.equals(originToolCode)) return "noredundancy";

		result = toolService.toolCodeCheck(code);			
		
		if(result > 0) {
			chk = "redundancy"; 	// 중복
		} else if(result == 0){
			chk = "noredundancy";	// 중복 아님
		}
				
		return chk;
	}
	
	// 장비 추가
	@PostMapping("/tool/form/insert")
	public String insertTool(@ModelAttribute ToolDTO toolDTO) {
		toolService.insertTool(toolDTO);
		return "redirect:/admin/tool";
	}

	
	// 장비 수정 폼
	@GetMapping("/tool/form/{id}")
	public String modifyTool(Model model, @PathVariable("id") Integer tool_id) {
		ToolDTO tool = toolService.toolDetail(tool_id);
		ArrayList<ToolCategoryDTO> category = toolService.getAllCategory();

		model.addAttribute("action", "/admin/tool/form/update");
		model.addAttribute("category", category);
		model.addAttribute("tool", tool);
		return "admin/admin_tool_form";
	}
	
	
	// 장비 수정 진행
	@PostMapping("/tool/form/update")
	public String updateTool(@ModelAttribute ToolDTO toolDTO) {
		toolService.updateTool(toolDTO);
		
		return "redirect:/admin/tool";
	}

	// 장비 삭제
	@GetMapping("/tool/delete/{id}")
	public String deleteTool(@PathVariable("id") Integer tool_id) {
		toolService.deleteTool(tool_id);

		return "redirect:/admin/tool";
	}

	
	// 대여 진행(목록에서 선택함)
	@GetMapping("/rent-return/rent/{reserve_id}")
	public String toolRental(HttpServletRequest request, HttpSession session, @PathVariable("reserve_id") Integer reserve_id) {
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		
		if(!session.getAttribute("user_grant").equals("ADMIN")) {
			return "redirect:/login";
		}
		
		rentalService.insertRental(reserve_id);
		
    	request.setAttribute("msg", "대여 성공");
        request.setAttribute("url", "/"+prevURL);
		
        return "fragments/alert";
	}

	
	// 반납 진행(목록에서 선택함)
	@GetMapping("/rent-return/return/{tool_code}/{rental_id}/{user_id}")
	public String toolReturn(HttpServletRequest request, HttpSession session, @PathVariable("tool_code") String tool_code , 
								@PathVariable("rental_id") Integer rental_id, @PathVariable("user_id") Integer user_id) {
		// 이전 페이지 URL
		String prevURL = request.getHeader("referer").substring(22);
		
		if(!session.getAttribute("user_grant").equals("ADMIN")) {
			return "redirect:/login";
		}
		
		rentalService.toolReturn(tool_code, rental_id, user_id);
		
    	request.setAttribute("msg", "반납 성공");
        request.setAttribute("url", "/"+prevURL);
		
        return "fragments/alert";
	}

	
	
	
}
