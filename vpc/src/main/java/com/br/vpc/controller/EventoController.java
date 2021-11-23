package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.service.EmailService;
import com.br.vpc.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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
        rp.setContentType("text/csv");
        String arq = "eventos.csv";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        List<EventoModel> eventos = eventoService.listar();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(rp.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Id", "Titulo", "Descrição", "Data inicio", "Data fim", "Tipo", "Status"};
        String[] nameMapping = {"idEvento","titulo","descricao","dataInicio","dataEncerramento","tipoEvento","status"};

        csvWriter.writeHeader(csvHeader);

        for (EventoModel e : eventos){
            csvWriter.write(e,nameMapping);
        }

        csvWriter.close();

    }
}
