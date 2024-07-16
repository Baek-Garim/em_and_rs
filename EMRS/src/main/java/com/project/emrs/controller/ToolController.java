package com.project.emrs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.emrs.dto.Tool;
import com.project.emrs.service.ToolService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("tool")
public class ToolController {
	
	@Autowired
	ToolService toolService;
	
	@GetMapping("toolList")
	public String home(Model model, HttpSession session) {
		ArrayList<Tool> toolList = toolService.toolList();
		for(Tool i: toolList) {
			i.setCategory_name(toolService.selectCate(i.getCategory_id()));
		}
		model.addAttribute("toolList", toolList);
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("user_name", session.getAttribute("user_name"));
		model.addAttribute("user_grant", session.getAttribute("user_grant"));
		return "toolList/toolList";
	}

	@GetMapping("detail")
	public String read(Model model, @RequestParam(name = "toolId") int toolId) {
	    log.debug("detail:", toolId);
	    Tool tool = toolService.toolDetail(toolId);
	    log.debug("detail", tool);
	    model.addAttribute("tool", tool);
	    
	    return "toolList/toolDetail";
	}
	
	@GetMapping("sortCategory")
	public String sortCategory(Model model, @RequestParam(name = "category") int category) {
		ArrayList<Tool> toolList = toolService.sortCategory(category);
		for(Tool i: toolList) {
			i.setCategory_name(toolService.selectCate(i.getCategory_id()));
		}
		model.addAttribute("toolList", toolList);
		
		return "toolList/toolList";
	}
	
} // controller
