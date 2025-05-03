package com.descomplicadoc.descomplicadoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class carregarController {

    @GetMapping("/carregando")
    public ModelAndView carregarPlano(@RequestParam String plano, @RequestParam Long id) {
        ModelAndView mv = new ModelAndView("carregar/index");
        mv.addObject("plano", plano);
        mv.addObject("id", id);
        return mv;
    }
}
