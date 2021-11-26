package com.br.vpc.controller;

import com.br.vpc.model.FornecedorModel;
import com.br.vpc.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService service;


    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody FornecedorModel model){
        service.save(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorModel>> lista(){
        List<FornecedorModel> fornecedores = service.findAll();
        if (fornecedores != null){
            return new ResponseEntity<>(fornecedores, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cnpj}")
    public void atualizar(@PathVariable String cnpj, String email, Integer telefone){
        service.update(cnpj, email, telefone);
    }

    @DeleteMapping("/{cnpj}")
    public void deletar(@PathVariable String cnpj){
        service.delete(cnpj);
    }


}
