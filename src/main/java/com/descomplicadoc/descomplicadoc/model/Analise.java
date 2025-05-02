package com.descomplicadoc.descomplicadoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "analises")
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @Column(columnDefinition = "TEXT")
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String explicacao;

    @Column(columnDefinition = "TEXT")
    private String sugestaoAcao;

    @Column(name = "data_analise", nullable = false, updatable = false)
    private Timestamp dataAnalise;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public String getSugestaoAcao() {
        return sugestaoAcao;
    }

    public void setSugestaoAcao(String sugestaoAcao) {
        this.sugestaoAcao = sugestaoAcao;
    }

    public Timestamp getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Timestamp dataAnalise) {
        this.dataAnalise = dataAnalise;
    }
}

