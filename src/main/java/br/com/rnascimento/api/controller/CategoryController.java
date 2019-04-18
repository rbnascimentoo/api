package br.com.rnascimento.api.controller;

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

import br.com.rnascimento.api.dtos.CategoryDTO;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Category")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "Find all Category.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<CategoryDTO>>> findAll() {
		Response<List<CategoryDTO>> response = new Response<List<CategoryDTO>>();
		response.setData(this.categoryService.findAll());
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Create a new Category.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<CategoryDTO>> create(@Valid @RequestBody CategoryDTO CategoryDto, BindingResult result){
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.categoryService.saveOrUpdate(CategoryDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Update an existing Category.")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<CategoryDTO>> update(@Valid @RequestBody CategoryDTO CategoryDto, BindingResult result){
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.categoryService.saveOrUpdate(CategoryDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Find Category by id.")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<CategoryDTO>> findById(@PathVariable(value = "id") Long id) {
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		response.setData(this.categoryService.findById(id));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete an existing Category.")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteById(@PathVariable(value = "id") Long id) {
		this.categoryService.deleteById(id);
	}

}