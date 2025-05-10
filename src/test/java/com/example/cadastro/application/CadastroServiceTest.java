package com.example.cadastro.application;

import com.example.cadastro.domain.Cadastro;
import com.example.cadastro.infrastructure.repository.CadastroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastroServiceTest {

    private CadastroRepository cadastroRepository;
    private CadastroService cadastroService;

    @BeforeEach
    void setUp() {
        cadastroRepository = mock(CadastroRepository.class);
        cadastroService = new CadastroService(cadastroRepository);
    }

    @Test
    void deveCriarCadastro() {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome("Eduardo");
        cadastro.setSobrenome("Silva");
        cadastro.setIdade(30);
        cadastro.setPais("Brasil");

        when(cadastroRepository.save(any(Cadastro.class))).thenReturn(cadastro);

        Cadastro criado = cadastroService.salvarCadastro(cadastro);

        assertNotNull(criado);
        assertEquals("Eduardo", criado.getNome());
        verify(cadastroRepository, times(1)).save(cadastro);
    }

    @Test
    void deveBuscarCadastroPorId() {
        Cadastro cadastro = new Cadastro();
        cadastro.setId(1L);
        cadastro.setNome("Maria");

        when(cadastroRepository.findById(1L)).thenReturn(Optional.of(cadastro));

        Cadastro encontrado = cadastroService.buscarCadastro(1L);

        assertNotNull(encontrado);
        assertEquals("Maria", encontrado.getNome());
        verify(cadastroRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarNuloQuandoCadastroNaoExiste() {
        when(cadastroRepository.findById(2L)).thenReturn(Optional.empty());

        Cadastro encontrado = cadastroService.buscarCadastro(2L);

        assertNull(encontrado);
        verify(cadastroRepository, times(1)).findById(2L);
    }

    @Test
    void deveListarTodosOsCadastros() {
        Cadastro cadastro1 = new Cadastro();
        cadastro1.setNome("João");

        Cadastro cadastro2 = new Cadastro();
        cadastro2.setNome("Ana");

        when(cadastroRepository.findAll()).thenReturn(List.of(cadastro1, cadastro2));

        List<Cadastro> cadastros = cadastroService.listarCadastros();

        assertEquals(2, cadastros.size());
        assertEquals("João", cadastros.get(0).getNome());
        assertEquals("Ana", cadastros.get(1).getNome());
        verify(cadastroRepository, times(1)).findAll();
    }

    @Test
    void deveAtualizarCadastroExistente() {
        Cadastro cadastro = new Cadastro();
        cadastro.setId(3L);
        cadastro.setNome("Carlos");
        cadastro.setSobrenome("Ferreira");
        cadastro.setIdade(40);
        cadastro.setPais("Portugal");

        Cadastro novoCadastro = new Cadastro();
        novoCadastro.setNome("Carlos");
        novoCadastro.setSobrenome("Silva");
        novoCadastro.setIdade(41);
        novoCadastro.setPais("Brasil");

        when(cadastroRepository.findById(3L)).thenReturn(Optional.of(cadastro));
        when(cadastroRepository.save(any(Cadastro.class))).thenReturn(novoCadastro);

        Cadastro atualizado = cadastroService.atualizarCadastro(3L, novoCadastro);

        assertNotNull(atualizado);
        assertEquals("Carlos", atualizado.getNome());
        assertEquals("Silva", atualizado.getSobrenome());
        assertEquals(41, atualizado.getIdade());
        assertEquals("Brasil", atualizado.getPais());
        verify(cadastroRepository, times(1)).save(novoCadastro);
    }

    @Test
    void naoDeveAtualizarCadastroInexistente() {
        Cadastro novoCadastro = new Cadastro();
        novoCadastro.setNome("Lucas");

        when(cadastroRepository.findById(99L)).thenReturn(Optional.empty());

        Cadastro atualizado = cadastroService.atualizarCadastro(99L, novoCadastro);

        assertNull(atualizado);
        verify(cadastroRepository, times(1)).findById(99L);
    }

    @Test
    void deveExcluirCadastro() {
        doNothing().when(cadastroRepository).deleteById(4L);

        cadastroService.excluirCadastro(4L);

        verify(cadastroRepository, times(1)).deleteById(4L);
    }
}