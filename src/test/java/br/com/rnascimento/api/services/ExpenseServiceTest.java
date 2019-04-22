package br.com.rnascimento.api.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.dtos.ExpenseDTO;
import br.com.rnascimento.api.entities.Category;
import br.com.rnascimento.api.entities.Expense;
import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.repositories.ExpenseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseServiceTest {

	@InjectMocks
	private ExpenseService expenseSevice;
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@Test
	public void findAllExpenses() {
		
		Category category = Category.builder().id(1L).name("category Test").build();
		
		User user = User.builder().id(1L).name("User test").build();
		
		Expense expense = Expense.builder().id(1L).amount(new BigDecimal(100)).category(category).user(user).build();
		
		List<Expense> listExpense = Arrays.asList(expense);
		
		Mockito.when(this.expenseRepository.findAll()).thenReturn(listExpense);
		
		List<ExpenseDTO> listExpenseDTO = this.expenseSevice.findAll();
		
		Mockito.verify(this.expenseRepository).findAll();
		
		Assert.assertThat(listExpenseDTO, CoreMatchers.notNullValue());
		
	}
	
	@Test
	public void saveOrUpdateExpenses() {
		
		Expense expense = Expense.builder().build();
		
		Mockito.when(this.expenseRepository.save(expense)).thenReturn(Expense.builder().id(1L).build());
		
		ExpenseDTO expenseDTOReturn = this.expenseSevice.saveOrUpdate(ExpenseDTO.builder().build());
		
		Mockito.verify(this.expenseRepository).save(expense);
		
		Assert.assertThat(expenseDTOReturn.getId(), CoreMatchers.notNullValue());
	}
	
	@Test
	public void findExpense() {

		Optional<Expense> expense = Optional.of(Expense.builder().id(1L).build());
		
		Mockito.when(this.expenseRepository.findById(1L)).thenReturn(expense);
		
		ExpenseDTO expenseDTO = this.expenseSevice.findById(1L);
		
		Mockito.verify(this.expenseRepository).findById(1L);
		
		Assert.assertThat(expenseDTO, CoreMatchers.notNullValue());
		
	}
	
	@Test
	public void deleteExpense() {

		Expense expense = Expense.builder().id(1L).build();

		this.expenseSevice.deleteById(1L);

		Mockito.verify(this.expenseRepository).deleteById(expense.getId());
	}

}