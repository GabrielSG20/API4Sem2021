package com.br.vpc.controller;

import com.br.vpc.model.UsuarioModel;
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
    public ResponseEntity<Void> cadastroOrganizador(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            organizadorService.cadastrar(usuarioModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarOrganizador(){
        try {
            List<UsuarioModel> listOrgs = organizadorService.listar();
            return new ResponseEntity<>(listOrgs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/org")
    public ResponseEntity<Void> atualizarOrganizador(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            organizadorService.aprovarOrg(usuarioModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarOrganizador(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            organizadorService.deletar(usuarioModel.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
