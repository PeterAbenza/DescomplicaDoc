package com.descomplicadoc.descomplicadoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class homeController {
	
	@GetMapping("/")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("home/index");
		return mv;
	}
}
