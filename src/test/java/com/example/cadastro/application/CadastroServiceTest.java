package com.example.cadastro.application;

import com.example.cadastro.domain.Cadastro;
import com.example.cadastro.infrastructure.repository.CadastroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroServiceTest {

    private final CadastroRepository cadastroRepository = Mockito.mock(CadastroRepository.class);
    private final CadastroService cadastroService = new CadastroService(cadastroRepository);

    @Test
    void deveCriarCadastro() {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome("Eduardo");
        cadastro.setSobrenome("Silva");
        cadastro.setIdade(30);
        cadastro.setPais("Brasil");

        Mockito.when(cadastroRepository.save(cadastro)).thenReturn(cadastro);

        Cadastro criado = cadastroService.salvarCadastro(cadastro);
        assertNotNull(criado);
        assertEquals("Eduardo", criado.getNome());
    }
}