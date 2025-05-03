package com.descomplicadoc.descomplicadoc.service;

import com.descomplicadoc.descomplicadoc.model.Plano;
import com.descomplicadoc.descomplicadoc.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public Plano findPlanoById(Long id) {
        return planoRepository.findById(id).orElse(null);
    }
}
