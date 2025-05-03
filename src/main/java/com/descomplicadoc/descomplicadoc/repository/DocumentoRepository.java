package com.descomplicadoc.descomplicadoc.repository;

import com.descomplicadoc.descomplicadoc.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
