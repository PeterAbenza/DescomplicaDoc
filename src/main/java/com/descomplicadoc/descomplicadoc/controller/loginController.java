package com.descomplicadoc.descomplicadoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
	@GetMapping("/login")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("login/index");
		return mv;
	}
}
