package com.augustocorreia.cadastro.services;

import com.augustocorreia.cadastro.exception.BadRequestException;
import com.augustocorreia.cadastro.mappers.ClienteMapper;
import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import com.augustocorreia.cadastro.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    @Mock
    private ClienteRepository repository;
    @Mock
    private ClienteMapper clienteMapper;

    private ClienteServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClienteServiceImpl(repository, clienteMapper);
    }
    @Test
    void findById() {
        Long id = 1L;
        underTest.findById(id);
    }

    @Test
    void getAllUsers() {
        Pageable pageable = PageRequest.of(0,10);
        underTest.getAllUsers(pageable);
        verify(repository).findAll(pageable);
    }

    @Test
    void addUser() {
        ClienteRequest cliente = getClienteRequest();
        underTest.addUser(cliente);
        verify(repository).save(clienteMapper.convertRequestToUser(cliente,new Cliente()));
    }

    @Test
    void updateUser() {
        ClienteRequest cliente = getClienteRequest();
        ResponseEntity<Cliente> entity = underTest.addUser(cliente);
        underTest.updateUser(cliente,1L);
        verify(repository)
                .save(clienteMapper.convertRequestToUser(cliente,new Cliente()));
    }

    @Test
    void getAllUsersBySearch() {
        Pageable pageable = PageRequest.of(0,10);
        String search = "augusto";
        underTest.getAllUsersBySearch(pageable,search);
        verify(repository).search(search,pageable);
    }

    @Test
    void deleteUser() {
        Long id = 1l;
        given(repository.existsById(id)).willReturn(true);
        underTest.deleteUser(id);
        verify(repository).deleteById(id);

    }

    @Test
    void throwDeleteUser() {
        Long id = 1l;
        given(repository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> underTest.deleteUser(id))
               .isInstanceOf(BadRequestException.class)
             .hasMessageContaining("Cliente com id " + id + " não encontrado");

        verify(repository,never()).deleteById(any());

    }

    private Cliente getCliente() {
        return new Cliente(
                1L,
                23,
                "Augusto",
                "000222111",
                "acl@acl.com"
        );
    }
    private ClienteRequest getClienteRequest() {
        return new ClienteRequest(
                23,
                "Augusto",
                "000222111",
                "acl@acl.com"
        );
    }
}