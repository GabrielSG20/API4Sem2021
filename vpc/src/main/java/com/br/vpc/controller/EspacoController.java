package com.br.vpc.controller;

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

    @PutMapping("/update/{cap1}/{cap2}")
    public ResponseEntity<Void> atualizarCap(@PathVariable Integer cap1, @PathVariable Integer cap2) {
        espacoService.atualizarEspaco(cap1,cap2);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Integer>> listarespacos() {
        List<Integer> capEsp = espacoService.listarEspaco();
        return new ResponseEntity<>(capEsp, HttpStatus.OK);
    }

}
