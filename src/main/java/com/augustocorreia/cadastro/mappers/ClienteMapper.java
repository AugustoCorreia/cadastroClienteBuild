package com.augustocorreia.cadastro.mappers;

import org.springframework.stereotype.Component;

import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import org.springframework.util.ObjectUtils;

@Component
public class ClienteMapper {

    public Cliente convertRequestToUser(ClienteRequest clienteRequest, Cliente CLiente){

        if(!ObjectUtils.isEmpty(clienteRequest)){
            if (!ObjectUtils.isEmpty(clienteRequest.getEmail())) CLiente.setEmail(clienteRequest.getEmail());
            if (!ObjectUtils.isEmpty(clienteRequest.getIdade())) CLiente.setIdade(clienteRequest.getIdade());
            if (!ObjectUtils.isEmpty(clienteRequest.getNome())) CLiente.setNome(clienteRequest.getNome());
            if (!ObjectUtils.isEmpty(clienteRequest.getDocumento())) CLiente.setDocumento(clienteRequest.getDocumento());
        }
    	return CLiente;

    }
}
