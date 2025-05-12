package com.example.cadastro;

import com.example.cadastro.infrastructure.repository.CadastroRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication( scanBasePackages = "com.example.cadastro", exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories( basePackageClasses = CadastroRepository.class)
@ComponentScan("com.example.cadastro.domain")
public class CadastroApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadastroApplication.class, args);
    }
}