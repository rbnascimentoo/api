package br.com.rnascimento.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "user")
@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Find all users.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<UserDTO>>> findAll() {
		Response<List<UserDTO>> response = new Response<List<UserDTO>>();
		response.setData(this.userService.findAll());
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Create a new user.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO userDto, BindingResult result){
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.userService.saveOrUpdate(userDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Update an existing user.")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserDTO>> update(@Valid @RequestBody UserDTO userDto, BindingResult result){
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.userService.saveOrUpdate(userDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Find user by id.")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserDTO>> findById(@PathVariable(value = "id") Long id) {
		Response<UserDTO> response = new Response<UserDTO>();
		response.setData(this.userService.findById(id));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete an existing user.")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteById(@PathVariable(value = "id") Long id) {
		this.userService.deleteById(id);
	}
}