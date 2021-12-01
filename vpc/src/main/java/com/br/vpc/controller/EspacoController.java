package com.br.vpc.controller;

import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.service.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/espaco")
public class EspacoController {

    @Autowired
    EspacoService espacoService;
}
