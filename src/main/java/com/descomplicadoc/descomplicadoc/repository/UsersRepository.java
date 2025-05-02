package com.descomplicadoc.descomplicadoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.descomplicadoc.descomplicadoc.model.Usuario;

@Repository
public interface UsersRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
}
	