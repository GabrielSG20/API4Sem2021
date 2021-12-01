package com.br.vpc.controller;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.model.EventoModel;
import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.service.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/espaco")
public class EspacoController {

    @Autowired
    EspacoService espacoService;

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarCap(@PathVariable Integer cap1, @PathVariable Integer cap2) {
        espacoService.atualizarEspaco(cap1,cap2);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/espacos")
    public ResponseEntity<Void> listarespacos(@PathVariable String nome) {
        Integer capEsp = espacoService.listarEspaco(nome);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
