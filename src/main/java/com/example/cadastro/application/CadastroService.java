package com.example.cadastro.application;

import com.example.cadastro.domain.Cadastro;
import com.example.cadastro.infrastructure.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

//    public CadastroService(CadastroRepository cadastroRepository) {
//        this.cadastroRepository = cadastroRepository;
//    }

    public Cadastro salvarCadastro(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);
    }

    public Cadastro buscarCadastro(Long id) {
        return cadastroRepository.findById(id).orElse(null);
    }

    public List<Cadastro> listarCadastros() {
        return cadastroRepository.findAll();
    }
//
//    public Cadastro atualizarCadastro(Long id, Cadastro novoCadastro) {
//        Cadastro cadastroExistente = buscarCadastro(id);
//        if (cadastroExistente != null) {
//            cadastroExistente.setNome(novoCadastro.getNome());
//            cadastroExistente.setSobrenome(novoCadastro.getSobrenome());
//            cadastroExistente.setIdade(novoCadastro.getIdade());
//            cadastroExistente.setPais(novoCadastro.getPais());
//            return cadastroRepository.save(cadastroExistente);
//        }
//        return null;
//    }

    public void excluirCadastro(Long id) {
        cadastroRepository.deleteById(id);
    }
}