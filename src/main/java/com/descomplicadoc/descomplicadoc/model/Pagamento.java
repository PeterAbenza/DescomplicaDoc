package com.descomplicadoc.descomplicadoc.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipo;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "metodo_pagamento", length = 50)
    private String metodoPagamento;

    @Column(name = "data_pagamento", nullable = false, updatable = false)
    private Timestamp dataPagamento;

    public enum TipoPagamento {
        MENSALIDADE
    }

    public enum StatusPagamento {
        PENDENTE, PAGO, FALHA
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

