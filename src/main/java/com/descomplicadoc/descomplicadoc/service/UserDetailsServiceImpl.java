package com.descomplicadoc.descomplicadoc.service;

import java.util.Collections;
import java.util.Optional;

import com.descomplicadoc.descomplicadoc.model.Usuario;
import com.descomplicadoc.descomplicadoc.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        Usuario user = optionalUser.get();
        return new User(user.getEmail(), user.getSenha(), Collections.emptyList());
    }
}
