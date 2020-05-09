package br.com.rnascimento.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.dtos.UserLoginDTO;
import br.com.rnascimento.api.dtos.UserSaveUpdateDTO;
import br.com.rnascimento.api.models.PageModel;
import br.com.rnascimento.api.models.PageRequestModel;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.security.JwtManager;
import br.com.rnascimento.api.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "user", tags = "Users", description = "Endpoints of Users")
@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtManager jwtManager;
	
//	@Autowired
//	private AccessManager accessManager;

	@ApiOperation(value = "Find all users.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<UserDTO>>> findAll() {
		Response<List<UserDTO>> response = new Response<List<UserDTO>>();
		response.setData(this.userService.findAll());
		return ResponseEntity.ok(response);
	}
	
	@Secured({"ROLE_ADMINISTRATOR"})
	@ApiOperation(value = "Create a new user.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserDTO>> create(@RequestBody UserSaveUpdateDTO userSaveDTO, BindingResult result){
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.userService.save(userSaveDTO));
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("@accessManager.isOwner(#id)")
	@ApiOperation(value = "Update an existing user.")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<UserDTO>> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserSaveUpdateDTO userDto, BindingResult result){
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.userService.update(id, userDto));
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
	
	@ApiOperation(value = "Find user by login and password.")
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<String>> login(@RequestBody @Valid UserLoginDTO userDto) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword());
		Authentication auth = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		User userSecurity = (User) auth.getPrincipal();
		String login = userSecurity.getUsername();
		List<String> roles = userSecurity.getAuthorities().stream().map(a -> a.getAuthority())
				.collect(Collectors.toList());
		Response<String> response = new Response<String>();
		response.setData(jwtManager.createToken(login, roles));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Find all users paginator.")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<PageModel<UserDTO>>> findAllPaginator(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		PageRequestModel pageRequest = new PageRequestModel(page, size);
		Response<PageModel<UserDTO>> response = new Response<PageModel<UserDTO>>();
		response.setData(this.userService.listAllUserPaginator(pageRequest));
		return ResponseEntity.ok(response);
	}
	
	@Secured({"ROLE_ADMINISTRATOR"})
	@ApiOperation(value = "Update role an existing user.")
	@PatchMapping(value = "/role/{id}")
	public ResponseEntity<Response<?>> updateUserRole(
			@PathVariable(name = "id") Long id,
			@RequestBody UserDTO userDto){
		
		Response<Integer> response = new Response<Integer>();
		response.setData(this.userService.updateUserRole(userDto));
		return ResponseEntity.ok(response);
	}
	
}