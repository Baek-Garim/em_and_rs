package com.project.emrs.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AdminToolController {

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
	
	@GetMapping("/tool")
	public String showAdminToolPage(Model model) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
			
		ArrayList<ToolDTO> toolList = toolService.getAllTool();
		// 카테고리 정보
		ArrayList<ToolCategoryDTO> category = toolService.getAllCategory();
		
		model.addAttribute("barType", "tool");
		model.addAttribute("toolList", toolList);
		model.addAttribute("category", category);
		return "admin/admin_tool";
	}
	
	@GetMapping("/tool/form")
	public String showAdminToolFormPage(Model model) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		// 카테고리 정보
		ArrayList<ToolCategoryDTO> category = toolService.getAllCategory();
		
		ToolDTO tool = new ToolDTO();
		model.addAttribute("tool", tool);		
		model.addAttribute("category", category);
		model.addAttribute("barType", "tool");
		model.addAttribute("action", "/admin/tool/form/insert");
		return "admin/admin_tool_form";
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
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		toolService.insertTool(toolDTO);
		return "redirect:/admin/tool";
	}

	
	// 장비 수정 폼
	@GetMapping("/tool/form/{id}")
	public String modifyTool(Model model, @PathVariable("id") Integer tool_id) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
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
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		toolService.updateTool(toolDTO);
		
		return "redirect:/admin/tool";
	}

	// 장비 삭제
	@GetMapping("/tool/delete/{id}")
	public String deleteTool(@PathVariable("id") Integer tool_id) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		toolService.deleteTool(tool_id);

		return "redirect:/admin/tool";
	}
	
	// 장비 삭제
	@GetMapping("/tool/revive/{id}")
	public String reviveTool(@PathVariable("id") Integer tool_id) {
		if(!loginCheck().equals("OK")) {
			return "redirect:/"+ loginCheck();
		}
		
		toolService.reviveTool(tool_id);

		return "redirect:/admin/tool";
	}
	
	


	// 필터 & 정렬
	@RequestMapping(value = "/tool/list/filter", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testCheck(@RequestParam(value="category_id") String category_id, @RequestParam(value="tool_code") String tool_code,
										@RequestParam(value="visibility") String visibility){	
	
		tool_code = tool_code.trim();
		
		List<ToolDTO> rentalList = toolService.getFilterData(category_id, tool_code, visibility);
		
		return new ResponseEntity<List<ToolDTO>>(rentalList, HttpStatus.OK);
	}	
	
	
}
