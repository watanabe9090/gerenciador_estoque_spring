package com.gerenciador_estoque;

import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GerenciadorEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorEstoqueApplication.class, args);
	}

}
