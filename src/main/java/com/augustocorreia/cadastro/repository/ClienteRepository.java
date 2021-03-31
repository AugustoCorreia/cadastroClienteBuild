package com.augustocorreia.cadastro.repository;

import com.augustocorreia.cadastro.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @Query( nativeQuery = true, value = "SELECT * FROM Usuario u " +
            "WHERE LOWER(u.nome) like %:search% " +
            "OR LOWER(u.email) like %:search% "+
            "OR u.idade like %:search% "+
            "OR LOWER(u.documento) like %:search% order by u.nome" )
    Page<Cliente> search(@Param("search") String search, Pageable pageable);


}
