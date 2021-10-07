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
    public ResponseEntity<Void> cadastroEvento(@Valid @RequestBody EventoModel eventoModel){
        try {
            eventoService.cadastrar(eventoModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/aprovar/{titulo}")
    public ResponseEntity<Void> aprovarEvento(@PathVariable String titulo){
        try {
            eventoService.aprovarEvento(titulo);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete/{titulo}")
    public ResponseEntity<Void> deletar(@PathVariable String titulo){
        try {
            eventoService.deletar(titulo);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
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
