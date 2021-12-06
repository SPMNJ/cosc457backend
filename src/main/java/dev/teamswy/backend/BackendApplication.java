package dev.teamswy.backend;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dev.teamswy.backend.entity.FraternityHQ;
import dev.teamswy.backend.repository.IFraternityHQRepository;

@SpringBootApplication
@EnableJpaRepositories
public class BackendApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner demo(IFraternityHQRepository repository) {
	// 	return args -> {
	// 		FraternityHQ hq = new FraternityHQ();
	// 		hq.setLetters("ΦΚΨ");
	// 		hq.setName("Phi Kappa Psi Fraternity");
	// 		hq.setDate(LocalDate.of(1852, 2, 19));
	// 		hq.setAddress("Laurel Hall; 5395 Emerson Way; Indianapolis, IN 46226; USA");
	// 		hq.setChapters(0);
	// 		repository.save(hq);
	// 	};
	// }		
}
