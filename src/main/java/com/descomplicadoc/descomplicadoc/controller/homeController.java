package com.descomplicadoc.descomplicadoc.controller;

import com.descomplicadoc.descomplicadoc.model.Usuario;
import com.descomplicadoc.descomplicadoc.repository.UsersRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class homeController {

    @Autowired
    private UsersRepository usersRepository; 

    @GetMapping("/")
    public ModelAndView showHome(Authentication authentication, HttpSession session) {
        ModelAndView mv = new ModelAndView("home/index");

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String email = user.getUsername();
            
            

            // Obtenha o usuário do banco de dados
            Usuario usuario = usersRepository.findByEmail(email).orElse(null);

            if (usuario != null) {
                // Armazenar o usuário na sessão
                session.setAttribute("usuarioLogado", usuario);
                session.setAttribute("email", email);

                int documentosRestantes = getDocumentosRestantes(usuario);
                session.setAttribute("documentosRestantes", documentosRestantes);
                mv.addObject("documentosRestantes", documentosRestantes);
            }
        }

        return mv;
    }

    private int getDocumentosRestantes(Usuario usuario) {
        if (usuario.getPlano() != null) {
            int limiteDiario = usuario.getPlano().getLimite_diario(); 
            int documentosGeradosHoje = usuario.getDocumentosHoje(); 

            return limiteDiario - documentosGeradosHoje; 
        }
        return 0; 
    }
}
