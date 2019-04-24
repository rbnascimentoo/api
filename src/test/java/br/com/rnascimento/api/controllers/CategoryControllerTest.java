package br.com.rnascimento.api.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.dtos.CategoryDTO;
import br.com.rnascimento.api.services.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private CategoryService categoryService;

	@Test
	public void findAllCategorys() {
		List<CategoryDTO> categoryDTOList = Arrays.asList(CategoryDTO.builder().id(1L).name("Category 1").build(), 
				CategoryDTO.builder().id(2L).name("Category 2").build());
		BDDMockito.when(this.categoryService.findAll()).thenReturn(categoryDTOList);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/fin-api/category/", String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	public void findCategoryById() {
		Long id = 1L;
		CategoryDTO categoryDTO = CategoryDTO.builder().id(id).build();
		BDDMockito.when(this.categoryService.findById(id)).thenReturn(categoryDTO);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/fin-api/category/" + id, String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}

}