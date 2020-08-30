package br.com.rnascimento.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
@ComponentScan
public class ApiApplication {
	
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		LOG.info("### Iniciando o serviço... ###");
		SpringApplication.run(ApiApplication.class, args);
	}


}