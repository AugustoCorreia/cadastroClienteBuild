package com.augustocorreia.cadastro.services;

import com.augustocorreia.cadastro.exception.BadRequestException;
import com.augustocorreia.cadastro.mappers.ClienteMapper;
import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import com.augustocorreia.cadastro.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ResponseEntity<Cliente> findById(Long id) {
        return clienteRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record
                )).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Page<Cliente> getAllUsers(Pageable pageable) {
         return clienteRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<Cliente> addUser(ClienteRequest cliente) {
        return ResponseEntity.ok().body(
                clienteRepository.save(
                        clienteMapper.convertRequestToUser(cliente,new Cliente()))
        );
    }

    @Override
    public ResponseEntity<Cliente> updateUser(ClienteRequest cliente, Long id) {
        return clienteRepository.findById(id)
                .map(record -> {
                   Cliente clienteUpdated = clienteMapper.convertRequestToUser(cliente,record) ;
                   return ResponseEntity.ok().body(
                           clienteRepository.save(clienteUpdated)
                   );
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Page<Cliente> getAllUsersBySearch(Pageable pageable, String search) {
        return clienteRepository.search(search.toLowerCase(),pageable);
    }

    @Override
    public void deleteUser(Long id) {
        if(!clienteRepository.existsById(id)) {
            throw new BadRequestException(
                    "Cliente com id " + id + " não encontrado");
        }
        clienteRepository.deleteById(id);
    }


}
