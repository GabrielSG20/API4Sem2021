package com.br.vpc.controller;

import com.br.vpc.model.EventoDTO;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.model.UsuarioModel;
import com.br.vpc.service.EventoEspacoService;
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
    public ResponseEntity<Void> cadastroUsuario(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            usuarioService.cadastrar(usuarioModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Void> atualizarUsuario(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            usuarioService.atualizar(usuarioModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletarUsuario(@Valid @RequestBody UsuarioModel usuarioModel){
        try {
            usuarioService.deletar(usuarioModel.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        try {
            List<UsuarioModel> usuarios = usuarioService.listar();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
