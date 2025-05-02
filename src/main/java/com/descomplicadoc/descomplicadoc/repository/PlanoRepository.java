package com.descomplicadoc.descomplicadoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.descomplicadoc.descomplicadoc.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {
    // Método para buscar um plano pelo ID
}
