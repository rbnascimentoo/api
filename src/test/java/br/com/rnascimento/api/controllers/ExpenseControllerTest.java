package br.com.rnascimento.api.controllers;

import java.math.BigDecimal;
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
import br.com.rnascimento.api.dtos.ExpenseDTO;
import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.services.ExpenseService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExpenseControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private ExpenseService expenseService;

	@Test
	public void findAllExpenses() {
		
		UserDTO userDTO = UserDTO.builder().id(1L).name("User 1").build();
		CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Categoy 1").build();
		
		List<ExpenseDTO> expenseDTOList = Arrays.asList(
				ExpenseDTO.builder().id(1L).amount(new BigDecimal(1500)).category(categoryDTO).user(userDTO).build(), 
				ExpenseDTO.builder().id(2L).amount(new BigDecimal(1000)).category(categoryDTO).user(userDTO).build());
		BDDMockito.when(this.expenseService.findAll()).thenReturn(expenseDTOList);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/fin-api/expense/", String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	public void findExpenseById() {
		Long id = 1L;
		UserDTO userDTO = UserDTO.builder().id(id).name("User 1").build();
		CategoryDTO categoryDTO = CategoryDTO.builder().id(id).name("Categoy 1").build();
		ExpenseDTO expenseDTO = ExpenseDTO.builder().id(id).amount(new BigDecimal(1500)).category(categoryDTO).user(userDTO).build();
		
		BDDMockito.when(this.expenseService.findById(id)).thenReturn(expenseDTO);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/fin-api/expense/" + id, String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}

}