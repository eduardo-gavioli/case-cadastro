package com.example.cadastro.domain;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "cadastro")
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private int idade;
    private String pais;
}