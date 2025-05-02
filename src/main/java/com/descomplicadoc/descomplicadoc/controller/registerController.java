package com.descomplicadoc.descomplicadoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class registerController {
	@GetMapping("/register")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("registro/index");
		return mv;
	}
}
