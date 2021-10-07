package com.br.vpc.controller;

import com.br.vpc.model.EventoModel;
import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/company-user")
public class OrganizadorController {

    @Autowired
    OrganizadorService organizadorService;

    @PostMapping
    public ResponseEntity<Void> cadastroOrganizador(@RequestBody @Valid OrganizadorModel organizadorModel){
            organizadorService.cadastrar(organizadorModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorModel>> listarOrganizador(){
        try {
            List<OrganizadorModel> listOrgs = organizadorService.listar();
            return new ResponseEntity<>(listOrgs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/org")
    public ResponseEntity<Void> atualizarOrganizador(@Valid @RequestBody OrganizadorModel organizadorModel){
        try {
            organizadorService.aprovarOrg(organizadorModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarOrganizador(@Valid @RequestBody OrganizadorModel organizadorModel){
        try {
            organizadorService.deletar(organizadorModel.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
