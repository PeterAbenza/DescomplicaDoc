package com.descomplicadoc.descomplicadoc.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.descomplicadoc.descomplicadoc.model.Usuario;

public class UsuarioService {

    public void verificarResetDiario(Usuario usuario) {
        Calendar hoje = Calendar.getInstance();
        hoje.setTime(new Date());
        int diaAtual = hoje.get(Calendar.DAY_OF_YEAR);

        Calendar dataCriacao = Calendar.getInstance();
        dataCriacao.setTime(usuario.getDataCriacao());
        int diaDeCriacao = dataCriacao.get(Calendar.DAY_OF_YEAR);

        if (diaAtual != diaDeCriacao) {
            usuario.setDocumentosHoje(0);
            usuario.setDataCriacao(new Timestamp(System.currentTimeMillis()));
        }
    }

    public void enviarDocumento(Usuario usuario) {
        verificarResetDiario(usuario);
        
        if (usuario.getDocumentosHoje() >= usuario.getPlano().getLimite_diario()) {
            throw new RuntimeException("Limite de documentos diário atingido. Tente novamente amanhã.");
        }

        usuario.setDocumentosHoje(usuario.getDocumentosHoje() + 1);
    }
}
