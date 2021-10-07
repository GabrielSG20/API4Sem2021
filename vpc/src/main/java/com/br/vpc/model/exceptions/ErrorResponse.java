package com.br.vpc.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final Integer code;
    private final String status;
    private final String object;
    private final List<ErrorObject> errors;
}