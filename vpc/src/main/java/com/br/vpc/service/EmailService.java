package com.br.vpc.service;

import com.br.vpc.constantes.EmailConstantes;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender sender;

    public void envioEmailCadastro(EventoModel eventoModel) {
        if (eventoModel != null) {
            for (UsuarioModel endercoEmail : eventoModel.getConvidados()) {
                String contato = endercoEmail.getEmail();
                definicoesEmail(contato, EmailConstantes.MENSAGEM_EMAIL_CADASTRO,
                        EmailConstantes.ASSUNTO_EMAIL_CADASTRO+eventoModel.getTitulo());
            }
            definicoesEmail(eventoModel.getOrg().getEmail(), EmailConstantes.MENSAGEM_EMAIL_ENVIADO_CONVIDADOS,
                    EmailConstantes.ASSUNTO_EMAIL_EVENTO_CRIADO+eventoModel.getTitulo());
        }
    }

    public void envioEmailEventoAprovado(EventoModel eventoModel) {
        if (eventoModel != null) {
            for (UsuarioModel endercoEmail : eventoModel.getConvidados()) {
                String contato = endercoEmail.getEmail();
                definicoesEmail(contato, EmailConstantes.MENSAGEM_EMAIL_CONVIDADO,
                        EmailConstantes.ASSUNTO_EMAIL_CONFIRMA_PRESENCA+eventoModel.getTitulo());
            }
            definicoesEmail(eventoModel.getOrg().getEmail(), EmailConstantes.MENSAGEM_EMAIL_CONFIRMACAO_EVENTO,
                    EmailConstantes.ASSUNTO_EMAIL_EVENTO_APROVADO+eventoModel.getTitulo());
        }
    }

    public void envioEmailEventoReprovado(EventoModel eventoModel, String comentario) {
        if (eventoModel != null) {
            definicoesEmail(eventoModel.getOrg().getEmail(), EmailConstantes.MENSAGEM_EMAIL_EVENTO_NEGADO + comentario,
                    EmailConstantes.ASSUNTO_EMAIL_EVENTO_NEGADO+eventoModel.getTitulo());
        }
    }

    public void envioEmailParticiparEvento(UsuarioModel usu, EventoModel eventoModel) {
        if (usu != null) {
            definicoesEmail(usu.getEmail(), EmailConstantes.MENSAGEM_EMAIL_PARTICIPAR_EVENTO,
                    EmailConstantes.ASSUNTO_EMAIL_PARTICIPAR_EVENTO+eventoModel.getTitulo());
        }
    }

    public void envioEmailCadastroUsu(UsuarioModel usu) {
        if (usu != null) {
            definicoesEmail(usu.getEmail(), EmailConstantes.MENSAGEM_EMAIL_CADASTRO_REALIZADO,
                    EmailConstantes.ASSUNTO_EMAIL_CADASTRO_REALIZADO+usu.getNomeCompleto());
        }
    }

    public void definicoesEmail(String contato, String texto, String assunto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(texto);
        message.setSubject(assunto);
        message.setTo(contato);
        try {
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
