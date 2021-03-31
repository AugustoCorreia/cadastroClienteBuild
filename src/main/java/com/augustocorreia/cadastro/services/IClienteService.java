package com.augustocorreia.cadastro.services;

import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IClienteService {
    ResponseEntity<Cliente> findById(Long id);

    Page<Cliente> getAllUsers(Pageable pageable);

    ResponseEntity<Cliente> addUser(ClienteRequest cliente);

    void deleteUser(Long id);

    ResponseEntity<Cliente> updateUser(ClienteRequest cliente, Long id);

    Page<Cliente> getAllUsersBySearch(Pageable pageable, String search);
}
