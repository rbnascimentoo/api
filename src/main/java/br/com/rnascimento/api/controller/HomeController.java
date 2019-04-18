package br.com.rnascimento.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Fin")
@RestController
@RequestMapping(value = "/fin-api")
public class HomeController {

	@ApiOperation(value = "Initial Method")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String init() {
		return "Bem Vindo!";
	}
}