package com.Challenge;

import com.Challenge.Principal.Principal;
import com.Challenge.Repository.AutorRepository; // Nuevo import
import com.Challenge.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository; // Inyectamos el de autores

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Pasamos ambos repositorios al constructor
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}
}