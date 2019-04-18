package br.com.rnascimento.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableCaching
public class ApiApplication {
	
//	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
//		LOG.info("### Iniciando o serviço... ###");
		SpringApplication.run(ApiApplication.class, args);
	}

}