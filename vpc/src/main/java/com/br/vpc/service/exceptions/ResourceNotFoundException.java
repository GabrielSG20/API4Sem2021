package com.br.vpc.service.exceptions;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Recurso não encontrado." + id);
    }

    public ResourceNotFoundException(String id){ super("Recurso não encontrado." + id); }

}
