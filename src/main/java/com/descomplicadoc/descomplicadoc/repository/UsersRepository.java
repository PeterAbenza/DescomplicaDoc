package com.descomplicadoc.descomplicadoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.descomplicadoc.descomplicadoc.model.Usuario;

@Repository
public interface UsersRepository extends JpaRepository<Usuario, Long> {
    // Aqui você pode adicionar métodos personalizados, caso necessário
}
