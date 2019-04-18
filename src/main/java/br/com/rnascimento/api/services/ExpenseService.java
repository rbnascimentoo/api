package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.ExpenseDTO;
import br.com.rnascimento.api.entities.Expense;
import br.com.rnascimento.api.repositories.ExpenseRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class ExpenseService {

	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private ExpenseRepository expenseRepository;
	
	/**
	 * Find all Expense.
	 * 
	 * @return List<ExpenseDTO>
	 */
	@Cacheable("findAllExpense")
	public List<ExpenseDTO> findAll() {
		LOG.info("### Find all Expense... ###");
		List<Expense> listExpense = this.expenseRepository.findAll();
		if(listExpense.isEmpty() || listExpense == null) {
			return new ArrayList<ExpenseDTO>();
		}
		return ModelMapperUtil.converter(listExpense, ExpenseDTO.class);
	}

	/**
	 * Save a new Expense or Update an existing Expense. 
	 * 
	 * @param ExpenseDto
	 * @return ExpenseDTO
	 */
	@CacheEvict(allEntries = true, value = {"findAllExpense", "findByIdExpense"}, beforeInvocation = true)
	public ExpenseDTO saveOrUpdate(ExpenseDTO expenseDto) {
		LOG.info("### Saving a new Expense or Updating an existing Expense... ###");
		Expense expense = ModelMapperUtil.converter(expenseDto, Expense.class);
		expense = this.expenseRepository.save(expense);
		return ModelMapperUtil.converter(expense, ExpenseDTO.class);
	} 

	/**
	 * Find a Expense.
	 * 
	 * @param id
	 * @return ExpenseDTO
	 */
	@Cacheable("findByIdExpense")
	public ExpenseDTO findById(Long id) {
		LOG.info("### Find a Expense... ###");
		ExpenseDTO expenseDto = new ExpenseDTO();
		Optional<Expense> expense = this.expenseRepository.findById(id);
		if(expense.isPresent()) {
			expenseDto = ModelMapperUtil.converter(expense.get(), ExpenseDTO.class);
		}
		return expenseDto;
	}

	/**
	 * Delete a Expense.
	 * 
	 * @param id
	 */
	@CacheEvict(allEntries = true, value = {"findAllExpense", "findByIdExpense"}, beforeInvocation = true)
	public void deleteById(Long id) {
		LOG.info("### Deleting a Expense... ###");
		this.expenseRepository.deleteById(id);
	}
	
}