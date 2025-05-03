package com.descomplicadoc.descomplicadoc.controller;

import com.descomplicadoc.descomplicadoc.model.Plano;
import com.descomplicadoc.descomplicadoc.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pagamentoController {

    @Autowired
    private PlanoRepository planoRepository;

    @GetMapping("/pagamento")
    public ModelAndView showPagamento(@RequestParam String plano, @RequestParam Long id) {
        ModelAndView mv = new ModelAndView("pagamento/index");

        Plano planoObj = planoRepository.findById(id).orElse(null);
        if (planoObj == null) {
            mv.setViewName("erro/404");
            return mv;
        }

        mv.addObject("planoSelecionado", planoObj);
        return mv;
    }
}
