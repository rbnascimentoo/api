package br.com.rnascimento.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
//		LOG.info("### Iniciando o serviço... ###");
		SpringApplication.run(ApiApplication.class, args);
	}

}
