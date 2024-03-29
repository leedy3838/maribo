package project.maribo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MariboApplication {

	public static void main(String[] args) {
		SpringApplication.run(MariboApplication.class, args);
	}
}