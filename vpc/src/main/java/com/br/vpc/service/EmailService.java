package com.br.vpc.service;

import com.br.vpc.constantes.EmailConstantes;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    JavaMailSender sender;

    public void enviar(String emailConvidados, String texto, String assunto) {
        String email = emailConvidados;
        String[] emailSeparados =  email.split(" ");
        for (String Contato: emailSeparados) {
            DefinicoesEmail(Contato, texto, assunto);
        }
    }

    public void DefinicoesEmail(String contato, String texto, String assunto){
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
