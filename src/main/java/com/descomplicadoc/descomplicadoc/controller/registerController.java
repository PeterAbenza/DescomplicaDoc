package com.descomplicadoc.descomplicadoc.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.descomplicadoc.descomplicadoc.model.Plano;
import com.descomplicadoc.descomplicadoc.model.Usuario;
import com.descomplicadoc.descomplicadoc.repository.PlanoRepository;
import com.descomplicadoc.descomplicadoc.repository.UsersRepository;

@Controller
public class registerController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PlanoRepository planoRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping("/register")
	public ModelAndView showRegister() {
		ModelAndView mv = new ModelAndView("registro/index");
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView createuser(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
			@RequestParam String confirmarSenha) {

		ModelAndView mv = new ModelAndView("registro/index");

		// Verifica se o e-mail já está cadastrado
		if (usersRepository.findByEmail(email).isPresent()) {
			mv.addObject("error", "Este e-mail já está cadastrado.");
			return mv;
		}
		
		if (nome.trim().contains(" ")) {
			mv.addObject("error", "O nome não pode conter espaços.");
		  
		    return mv;
		}

		// Verifica se a senha tem no mínimo 6 caracteres
		if (senha.length() < 6) {
			mv.addObject("error", "A senha deve ter no mínimo 6 caracteres.");
			return mv;
		}

		// Verifica se as senhas coincidem
		if (!senha.equals(confirmarSenha)) {
			mv.addObject("error", "As senhas não coincidem.");
			return mv;
		}

		Usuario newUser = new Usuario();
		newUser.setNome(nome);
		newUser.setEmail(email);
		newUser.setSenha(passwordEncoder.encode(senha));
		newUser.setDataCriacao(new Timestamp(System.currentTimeMillis()));
		newUser.setDocumentosHoje(0);

		Plano planoGratis = planoRepository.findById(1L).orElse(null);
		if (planoGratis != null) {
			newUser.setPlano(planoGratis);
		} else {
			mv.addObject("error", "Plano gratuito não encontrado.");
			return mv;
		}

		usersRepository.save(newUser);
		mv.addObject("success", "Usuário registrado com sucesso!");
		
		
		return new ModelAndView("redirect:/login");
	}

}
