package com.br.vpc.controller;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.UsuarioModel;
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
  
    @GetMapping("/exportaberto")
    public void exportCsvAprovadoAberto(HttpServletResponse rp) throws IOException {
        String arq = "eventos.csv";
        rp.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        CSVWriter cw = new CSVWriter(rp.getWriter());

        String[] csvTab = {"Id Evento", "Titulo", "Descrição", "Espaço", "Data/Hora Início", "Data/Hora fim", "Organizador","ID Organizador"};
        List<EventoModel> eventos = eventoService.listarAprovadosAberto();
        List<String[]> listEvt = new ArrayList<String[]>();

        listEvt.add(csvTab);

        for (EventoModel e : eventos) {
            for (EspacoModel esp : e.getNomeEspaco()) {

                String[] csvValue = {e.getIdEvento().toString(), e.getTitulo(), e.getDescricao(), esp.getNomeEspaco(), e.getDataInicio(), e.getDataEncerramento(), e.getOrg().getEmail(), e.getOrg().getIdOracle().toString()};
                listEvt.add(csvValue);
            }
        }
        cw.writeAll(listEvt);
        cw.close();
    }

    @GetMapping("/exportfechado")
    public void exportCsvAprovadoFechado(HttpServletResponse rp) throws IOException {
        String arq = "eventos.csv";
        rp.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        CSVWriter cw = new CSVWriter(rp.getWriter());

        String[] csvTab = {"Id Evento", "Titulo", "Descrição", "Espaço", "Data/Hora Início", "Data/Hora fim", "Organizador","ID Organizador"};
        List<EventoModel> eventos = eventoService.listarAprovadosFechado();
        List<String[]> listEvt = new ArrayList<String[]>();

        listEvt.add(csvTab);

        for (EventoModel e : eventos) {
            for (EspacoModel esp : e.getNomeEspaco()) {

                String[] csvValue = {e.getIdEvento().toString(), e.getTitulo(), e.getDescricao(), esp.getNomeEspaco(), e.getDataInicio(), e.getDataEncerramento(), e.getOrg().getEmail(), e.getOrg().getIdOracle().toString()};
                listEvt.add(csvValue);
            }
        }
        cw.writeAll(listEvt);
        cw.close();
    }

    @GetMapping("/exportconvidadoaberto")
    public void exportCsvConvidadoAberto(HttpServletResponse rp) throws IOException {
        String arq = "eventos.csv";
        rp.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        CSVWriter cw = new CSVWriter(rp.getWriter());

        String[] csvTab = {"Id Evento","Titulo Evento","Nome Convidado","Email convidado","Tipo de usuário"};
        List<EventoModel> eventos = eventoService.listarAprovadosAberto();
        List<String[]> listEvt = new ArrayList<String[]>();

        listEvt.add(csvTab);

        for (EventoModel e : eventos) {
            for (UsuarioModel usu : e.getConvidados()) {

                String[] csvValue = {e.getIdEvento().toString(),e.getTitulo(),usu.getNomeCompleto(),usu.getEmail(),usu.getTipoUsuario()};
                listEvt.add(csvValue);
            }
        }
        cw.writeAll(listEvt);
        cw.close();
    }

    @GetMapping("/exportconvidadofechado")
    public void exportCsvConvidadoFechado(HttpServletResponse rp) throws IOException {
        String arq = "eventos.csv";
        rp.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; + filename:" + arq;

        rp.setHeader(headerKey,headerValue);

        CSVWriter cw = new CSVWriter(rp.getWriter());

        String[] csvTab = {"Id Evento","Titulo Evento","Nome Convidado","Email convidado","Tipo de usuário"};
        List<EventoModel> eventos = eventoService.listarAprovadosFechado();
        List<String[]> listEvt = new ArrayList<String[]>();

        listEvt.add(csvTab);

        for (EventoModel e : eventos) {
            for (UsuarioModel usu : e.getConvidados()) {

                String[] csvValue = {e.getIdEvento().toString(),e.getTitulo(),usu.getNomeCompleto(),usu.getEmail(),usu.getTipoUsuario()};
                listEvt.add(csvValue);
            }
        }
        cw.writeAll(listEvt);
        cw.close();

    }
}
