package com.descomplicadoc.descomplicadoc.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.descomplicadoc.descomplicadoc.model.Usuario;

public class UsuarioService {

    // Método para verificar e resetar o contador diário
    public void verificarResetDiario(Usuario usuario) {
        // Verifica se o dia de hoje é diferente da data de criação do usuário
        Calendar hoje = Calendar.getInstance();
        hoje.setTime(new Date());
        int diaAtual = hoje.get(Calendar.DAY_OF_YEAR);

        Calendar dataCriacao = Calendar.getInstance();
        dataCriacao.setTime(usuario.getDataCriacao());
        int diaDeCriacao = dataCriacao.get(Calendar.DAY_OF_YEAR);

        if (diaAtual != diaDeCriacao) {
            // Resetar o contador de documentos do usuário
            usuario.setDocumentosHoje(0);
            // Atualiza a data de criação para o dia atual, evitando que o contador resete novamente
            usuario.setDataCriacao(new Timestamp(System.currentTimeMillis()));
        }
    }

    // Método para adicionar uma análise (ou enviar um novo documento)
    public void enviarDocumento(Usuario usuario) {
        // Verifica e reseta o contador de documentos se necessário
        verificarResetDiario(usuario);
        
        // Verifica se o usuário já atingiu o limite diário
        if (usuario.getDocumentosHoje() >= usuario.getPlano().getLimite_diario()) {
            throw new RuntimeException("Limite de documentos diário atingido. Tente novamente amanhã.");
        }

        // Se não atingiu o limite, permite o envio e incrementa o contador de documentos
        usuario.setDocumentosHoje(usuario.getDocumentosHoje() + 1);
        // Aqui você poderia também salvar o usuário atualizado no banco de dados
    }
}
