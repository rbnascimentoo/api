package br.com.rnascimento.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiApplication {
	
//	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
//		LOG.info("### Iniciando o serviço... ###");
		SpringApplication.run(ApiApplication.class, args);
	}

}