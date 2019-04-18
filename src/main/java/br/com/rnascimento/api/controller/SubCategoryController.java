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

import br.com.rnascimento.api.dtos.SubCategoryDTO;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.services.SubCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "SubCategory")
@RestController
@RequestMapping(value = "/SubCategory")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;

	@ApiOperation(value = "Find all SubCategorys.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<SubCategoryDTO>>> findAll() {
		Response<List<SubCategoryDTO>> response = new Response<List<SubCategoryDTO>>();
		response.setData(this.subCategoryService.findAll());
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Create a new SubCategory.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<SubCategoryDTO>> create(@Valid @RequestBody SubCategoryDTO SubCategoryDto, BindingResult result){
		Response<SubCategoryDTO> response = new Response<SubCategoryDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.subCategoryService.saveOrUpdate(SubCategoryDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Update an existing SubCategory.")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<SubCategoryDTO>> update(@Valid @RequestBody SubCategoryDTO SubCategoryDto, BindingResult result){
		Response<SubCategoryDTO> response = new Response<SubCategoryDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.subCategoryService.saveOrUpdate(SubCategoryDto));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Find SubCategory by id.")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<SubCategoryDTO>> findById(@PathVariable(value = "id") Long id) {
		Response<SubCategoryDTO> response = new Response<SubCategoryDTO>();
		response.setData(this.subCategoryService.findById(id));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete an existing SubCategory.")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteById(@PathVariable(value = "id") Long id) {
		this.subCategoryService.deleteById(id);
	}

}