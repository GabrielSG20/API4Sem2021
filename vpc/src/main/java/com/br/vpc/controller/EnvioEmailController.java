package com.br.vpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvioEmailController {

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/envioEmail")
    public void enviarEmail(){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Hello from Spring Boot Application");
            message.setSubject("Confirmação de presença");
            message.setTo("ramonzxz@icloud.com", "gabrielsoaresg20@gmail.com");
            try {
                sender.send(message);
                System.out.println("Email enviado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro ao enviar email.");
            }
    }

}
