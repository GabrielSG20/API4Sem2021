package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/eventos")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping("/create")
    public ResponseEntity<Void> cadastroEvento(@RequestBody @Valid EventoModel eventoModel){

            eventoService.cadastrar(eventoModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/aprovar/{titulo}")
    public ResponseEntity<Void> aprovarEvento(@PathVariable String titulo){
            eventoService.aprovarEvento(titulo);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{titulo}")
    public ResponseEntity<Void> deletar(@PathVariable String titulo){
            eventoService.deletar(titulo);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventoModel>> listarEventos() {
        try {
            List<EventoModel> listEvents = eventoService.listar();
            return new ResponseEntity<>(listEvents, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
