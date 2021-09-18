package com.br.vpc.controller;

import com.br.vpc.constantes.EmailConstantes;
import com.br.vpc.model.ConvidadosModel;
import com.br.vpc.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvioEmailController {

    private ConvidadosModel convidadosModel = new ConvidadosModel();

    @Autowired
    private EmailService emailService;

    @GetMapping("/envioEmail")
    public void EnvioEmail(){
        convidadosModel.setEmailConvidado("ramonzxz@gmail.com ramonzxz@icloud.com gabrielsoaresg20@gmail.com");
        emailService.enviar(convidadosModel.getEmailConvidado(),EmailConstantes.MENSAGEM_EMAIL,
                EmailConstantes.ASSUNTO_EMAIL_CONFIRMA_PRESENCA);
    }




}
