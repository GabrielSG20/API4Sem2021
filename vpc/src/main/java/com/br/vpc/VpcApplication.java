package com.br.vpc;

import com.br.vpc.model.ConvidadosModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(VpcApplication.class, args);

		ConvidadosModel convidadosModel = new ConvidadosModel();
		convidadosModel.setEmailConvidado("ramonzxz@gmail.com gabrielsoaresg@gmail.com");
		convidadosModel.OneEmail();
	}

}
