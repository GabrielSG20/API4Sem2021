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

    public void envioEmail(EventoModel eventoModel) {
        if (eventoModel != null) {
            for (UsuarioModel endercoEmail : eventoModel.getConvidados()) {
                String contato = endercoEmail.getEmail();
                definicoesEmail(contato, EmailConstantes.MENSAGEM_EMAIL_CONVIDADO,
                        EmailConstantes.ASSUNTO_EMAIL_CONFIRMA_PRESENCA);
            }
            definicoesEmail(eventoModel.getOrg().getEmail(), EmailConstantes.MENSAGEM_EMAIL_ENVIADO_CONVIDADOS,
                    EmailConstantes.ASSUNTO_EMAIL_EVENTO_CRIADO);
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
