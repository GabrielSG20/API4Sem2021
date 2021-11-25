package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.service.EmailService;
import com.br.vpc.service.EventoService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/eventos")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    EmailService emailService;


    //@PreAuthorize("hasRole('ROLE_ORG')")
    @PostMapping("/create")
    public ResponseEntity<Void> cadastroEvento(@RequestBody @Valid EventoModel eventoModel) {
        eventoService.cadastrar(eventoModel);
        emailService.envioEmailCadastro(eventoModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/aprovar/{id}")
    public ResponseEntity<Void> aprovarEvento(@PathVariable Integer id) {
        eventoService.aprovarEvento(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORG')")
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

    @GetMapping("/export")
    public void exportCSV(HttpServletResponse rp) throws IOException {
        String arq = "eventos.csv";
        rp.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        CSVWriter cw = new CSVWriter(rp.getWriter());

        String[] csvTab = {"Id Evento", "Titulo", "Descrição", "Data/Hora Início", "Data/Hora fim", "Tipo do evento", "Status", "Org"};
        List<EventoModel> eventos = eventoService.listar();
        List<String[]> listEvt = new ArrayList<String[]>();

        listEvt.add(csvTab);

        for (EventoModel e : eventos) {
            String[] csvValue = {e.getIdEvento().toString(), e.getTitulo(), e.getDescricao(), e.getDataInicio(), e.getDataEncerramento(), e.getTipoEvento(), e.getStatus().toString(), e.getOrg().getEmail()};

            listEvt.add(csvValue);
        }

        cw.writeAll(listEvt);
        cw.close();
    }
}
