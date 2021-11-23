package com.br.vpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class TesteController {

    @GetMapping("/teste")
    public void teste() {
    }
}
