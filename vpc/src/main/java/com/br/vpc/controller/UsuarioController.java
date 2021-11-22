package com.br.vpc.controller;

import com.br.vpc.model.UsuarioModel;
import com.br.vpc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/create")
    public ResponseEntity<Void> cadastroUsuario(@Valid @RequestBody UsuarioModel usuarioModel) {
            usuarioService.cadastrar(usuarioModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listar(){
        try {
            List<UsuarioModel> listOrgs = usuarioService.listar();
            return new ResponseEntity<>(listOrgs, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    //@PreAuthorize("hasRole('ROLE_ORG') or hasRole('ROLE_ADMIN') or hasRole('ROLE_INTERNO')" +
            //" or hasRole('ROlE_EXTERNO')")
    @GetMapping("/{email}")
    public ResponseEntity<UsuarioModel> listarUsuario(@PathVariable String email){
        UsuarioModel user = usuarioService.listarUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_INTERNO')")
    @PutMapping("/org/{email}/{cargoUsuario}")
    public ResponseEntity<Void> atualizarOrganizador(@PathVariable String email, @PathVariable String cargoUsuario){
            usuarioService.aprovarOrg(email, cargoUsuario);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletarUsuario(@Valid @RequestBody UsuarioModel usuarioModel) {
        usuarioService.deletar(usuarioModel.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}