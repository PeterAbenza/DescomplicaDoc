package com.descomplicadoc.descomplicadoc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {

    @GetMapping("/login")
    public ModelAndView showLogin(@RequestParam(value = "error", required = false) String error, Authentication authentication) {
        ModelAndView mv = new ModelAndView("login/index");

        // Verifica se há erro na autenticação
        if (error != null) {
            mv.addObject("errorMessage", "Email ou senha inválidos.");
        }

        return mv;
    }
}
