package com.augustocorreia.cadastro.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    public Cliente(Integer idade, String nome, String documento, String email) {
        this.idade = idade;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer idade;

    @Column
    private String nome;

    @Column(nullable = false,unique = true)
    private String documento;

    @Column(nullable = false,unique = true)
    private String email;

}
