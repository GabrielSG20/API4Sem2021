package com.br.vpc.service;

import com.br.vpc.model.FornecedorModel;
import com.br.vpc.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public void save(FornecedorModel model) {
        repository.save(model);
    }

    public List<FornecedorModel> findAll() {
        return repository.findAll();
    }

    public void update(String cnpj, String email, Integer telefone) {
       Optional<FornecedorModel> fornecedor = repository.findByCnpj(cnpj);
       FornecedorModel model = fornecedor.get();
       if (fornecedor != null){
           if (email != null){
               model.setEmailFornecedor(email);
           }
           if (telefone != null){
               model.setTelefoneFornecedor(telefone);
           }

           repository.save(model);
       }
    }

    public void delete(String cnpj) {
        repository.deleteByCnpj(cnpj);
    }
}
