package com.br.vpc.service;

import com.br.vpc.model.CategoriaModel;
import com.br.vpc.model.FornecedorModel;
import com.br.vpc.repository.CategoriaRepository;
import com.br.vpc.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void save(FornecedorModel model) {
        for(CategoriaModel cat:model.getCategorias()) {
            Integer id = categoriaRepository.findCategoriaByName(cat.getNomeCategoria());
            cat.setIdCategoria(id);
        }
        repository.save(model);
    }

    public List<FornecedorModel> findAll() {
        return repository.findAll();
    }

    public void update(String cnpj, String email, String telefone) {
       Optional<FornecedorModel> fornecedor = repository.findById(cnpj);
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
        repository.deleteById(cnpj);
    }

    public Optional<FornecedorModel> listaCnpj(String cnpj) {
        return repository.findById(cnpj);
    }
}
