package com.example.cadastro.infrastructure.api;

import com.example.cadastro.domain.Cadastro;
import com.example.cadastro.application.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    private  CadastroService cadastroService;

//    public CadastroController(CadastroService cadastroService) {
//        this.cadastroService = cadastroService;
//    }

    @PostMapping
    public Cadastro criarCadastro(@RequestBody Cadastro cadastro) {
        return cadastroService.salvarCadastro(cadastro);
    }

    @GetMapping("/{id}")
    public Cadastro buscarCadastro(@PathVariable Long id) {
        return cadastroService.buscarCadastro(id);
    }

    @GetMapping("/getAll")
    public List<Cadastro> listarCadastros() {
        return cadastroService.listarCadastros();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Reaioasao Caraiiio";
    }
//
//    @PatchMapping("/{id}")
//    public Cadastro atualizarCadastro(@PathVariable Long id, @RequestBody Cadastro cadastro) {
//        return cadastroService.atualizarCadastro(id, cadastro);
//    }

    @DeleteMapping("/{id}")
    public void excluirCadastro(@PathVariable Long id) {
        cadastroService.excluirCadastro(id);
    }
}