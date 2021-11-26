package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.service.EmailService;
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

    @Autowired
    EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<Void> cadastroEvento(@RequestBody @Valid EventoModel eventoModel) {
        eventoService.cadastrar(eventoModel);
        emailService.envioEmailCadastro(eventoModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity<Void> aprovarEvento(@PathVariable Integer id) {
        eventoService.aprovarEvento(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id, @RequestParam String comentario) {
        eventoService.deletar(id, comentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventoModel>> listarEventos() {
        List<EventoModel> listEvents = eventoService.listar();
        return new ResponseEntity<>(listEvents, HttpStatus.OK);
    }

    @GetMapping("/aprovados")
    public ResponseEntity<List<EventoModel>> listarAprovados() {
        List<EventoModel> listEvents = eventoService.listarAprovados();
        return new ResponseEntity<>(listEvents, HttpStatus.OK);
    }

    @GetMapping("/conflito-dia")
    public ResponseEntity<List<String>> mesmaData() {
        List<String> listEvents = eventoService.eventosMesmaData();
        return new ResponseEntity<>(listEvents, HttpStatus.OK);
    }

    @PutMapping("/participar/{id}/{email}")
    public ResponseEntity<Void> aprovarEvento(@PathVariable Integer id, @PathVariable String email) {
        eventoService.participar(id, email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
