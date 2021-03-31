package com.augustocorreia.cadastro.repository;

import com.augustocorreia.cadastro.models.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itSholdReturnClienteOnSearch() {

        //given
        Cliente cliente = getCliente();
        Pageable pageable = PageRequest.of(0,10);

        //when
        underTest.save(cliente);
        Page<Cliente> result = underTest.search("Augusto", pageable);
        //then

        assertThat(result.getTotalElements()==1).isTrue();

    }

    @Test
    void itSholdReturnNoneClienteOnSearch() {

        //given
        Pageable pageable = PageRequest.of(0,10);
        //when
        Page<Cliente> result = underTest.search("Augusto", pageable);
        //then
        assertThat(result.getTotalElements()==0).isTrue();

    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        // given

        Cliente cliente = getCliente();
        underTest.save(cliente);

        // when
        Optional<Cliente> expected = underTest.findByEmail(cliente.getEmail());

        // then
        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        // given
        String email = "acl@acl.com";

        // when
        Optional<Cliente> expected = underTest.findByEmail(email);

        // then
        assertThat(expected.isPresent()).isFalse();
    }

    private Cliente getCliente() {
        return new Cliente(
        		23,
                "Augusto",
                "000222111",
                "acl@acl.com"
        );
    }
}