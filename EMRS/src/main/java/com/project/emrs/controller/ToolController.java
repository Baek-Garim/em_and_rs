package com.project.emrs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.emrs.dto.TestDTO;
import com.project.emrs.dto.Tool;
import com.project.emrs.service.ToolService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("tool")
public class ToolController {
	
	@Autowired
	ToolService toolService;
	
	@GetMapping("toolList")
	public String home(Model model) {
		ArrayList<Tool> toolList = toolService.toolList();
		model.addAttribute("toolList", toolList);
		
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
		model.addAttribute("toolList", toolList);
		
		return "toolList/toolList";
	}
	
} // controller
