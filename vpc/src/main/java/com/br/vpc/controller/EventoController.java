package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/eventos")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping
    public ResponseEntity<Void> cadastroEvento(@Valid @RequestBody EventoModel eventoModel){
        try {
            eventoService.cadastrar(eventoModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping
    public ResponseEntity<Void> atualizaEvento(@Valid @RequestBody EventoModel eventoModel){
        try {
            eventoService.atualizar(eventoModel);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @DeleteMapping("/delete/{titulo}")
    public ResponseEntity<Void> deletar(@PathVariable(name = "titulo") String titulo){
        try {
            eventoService.deletar(titulo);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
