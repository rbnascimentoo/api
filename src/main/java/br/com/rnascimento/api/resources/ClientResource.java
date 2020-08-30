package br.com.rnascimento.api.resources;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(value = "client", tags = "Clients", description = "Endpoints of Clients")
@RestController
@RequestMapping(value = "/client")
public class ClientResource {


}
