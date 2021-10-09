package com.br.vpc.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Recurso não encontrado." + id);
    }

    public ResourceNotFoundException(String id){ super("Recurso não encontrado." + id); }

}
