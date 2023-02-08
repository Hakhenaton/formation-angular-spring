package fr.sncf.comere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.sncf.comere.users.repository.UserRepository;

@SpringBootApplication
public class ComereApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComereApplication.class, args);
	}

}
