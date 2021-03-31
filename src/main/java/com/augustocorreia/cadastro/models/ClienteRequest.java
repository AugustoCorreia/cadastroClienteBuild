package com.augustocorreia.cadastro.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteRequest {

    private Integer idade;
    private String nome;
    private String documento;
    private String email;



}
