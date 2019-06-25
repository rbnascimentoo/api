package br.com.rnascimento.api.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api")
@RestController
@RequestMapping(value = "/")
public class HomeResource {

	@ApiOperation(value = "Initial Method")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String init() {
		return "Bem Vindo!";
	}
}