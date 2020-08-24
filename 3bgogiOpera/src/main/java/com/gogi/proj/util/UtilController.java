package com.gogi.proj.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.proj.admin.project.vo.ProjectsVO;

@Controller
@RequestMapping("/alarm")
public class UtilController {

	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@RequestMapping(value="/popup.do", method=RequestMethod.GET)
	public String openAlarmPage(HttpServletRequest request, @RequestParam List<Object> projects, Model model) {
		
		logger.info("result = {}", projects.toString());
		
		model.addAttribute("projects", projects);
		return "common/alarm";
	}
}
