package com.descomplicadoc.descomplicadoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "planos")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer limite_diario;
    private Integer dias_teste;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private Timestamp criadoEm;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLimite_diario() {
        return limite_diario;
    }

    public void setLimite_diario(Integer limite_diario) {
        this.limite_diario = limite_diario;
    }

    public Integer getDias_teste() {
        return dias_teste;
    }

    public void setDias_teste(Integer dias_teste) {
        this.dias_teste = dias_teste;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }
}

