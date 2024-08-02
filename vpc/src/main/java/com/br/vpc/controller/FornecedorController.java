package com.br.vpc.controller;

import com.br.vpc.model.FornecedorModel;
import com.br.vpc.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService service;


    @PostMapping("/create")
    public ResponseEntity<Void> cadastrar(@RequestBody FornecedorModel model){
        service.save(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorModel>> lista(){
        List<FornecedorModel> fornecedores = service.findAll();
        return new ResponseEntity<>(fornecedores,HttpStatus.OK);
    }

    @GetMapping("/cnpj/{cnpj}")
    public Optional<FornecedorModel> listaPorCnpj(@PathVariable String cnpj){
        return service.listaCnpj(cnpj);
    }

    @PutMapping("/{cnpj}")
    public void atualizar(@PathVariable String cnpj, @RequestParam String email,
                          @RequestParam String telefone){
        service.update(cnpj, email, telefone);
    }

    @DeleteMapping("/{cnpj}")
    public void deletar(@PathVariable String cnpj){
        service.delete(cnpj);
    }


}
