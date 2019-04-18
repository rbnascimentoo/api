package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.CategoryDTO;
import br.com.rnascimento.api.entities.Category;
import br.com.rnascimento.api.repositories.CategoryRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class CategoryService {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Find all Category.
	 * 
	 * @return List<CategoryDTO>
	 */
	@Cacheable("findAllCategory")
	public List<CategoryDTO> findAll() {
		LOG.info("### Find all Category... ###");
		List<Category> listCategory = this.categoryRepository.findAll();
		if(listCategory.isEmpty() || listCategory == null) {
			return new ArrayList<CategoryDTO>();
		}
		return ModelMapperUtil.converter(listCategory, CategoryDTO.class);
	}

	/**
	 * Save a new Category or Update an existing Category. 
	 * 
	 * @param categoryDTO
	 * @return CategoryDTO
	 */
	@CacheEvict(allEntries = true, value = {"findAllCategory", "findByIdCategory"}, beforeInvocation = true)
	public CategoryDTO saveOrUpdate(@Valid CategoryDTO categoryDTO) {
		LOG.info("### Saving a new Category or Updating an existing Category... ###");
		Category category = ModelMapperUtil.converter(categoryDTO, Category.class);
		category = this.categoryRepository.save(category);
		return ModelMapperUtil.converter(category, CategoryDTO.class);
	}

	/**
	 * Find a Category.
	 * 
	 * @param id
	 * @return CategoryDTO
	 */
	@Cacheable("findByIdCategory")
	public CategoryDTO findById(Long id) {
		LOG.info("### Find a Category... ###");
		CategoryDTO categoryDTO = new CategoryDTO();
		Optional<Category> category = this.categoryRepository.findById(id);
		if(category.isPresent()) {
			categoryDTO = ModelMapperUtil.converter(category.get(), CategoryDTO.class);
		}
		return categoryDTO;
	}

	/**
	 * Delete a Category.
	 * 
	 * @param id
	 */
	@CacheEvict(allEntries = true, value = {"findAllCategory", "findByIdCategory"}, beforeInvocation = true)
	public void deleteById(Long id) {
		LOG.info("### Deleting a Expense... ###");
		this.categoryRepository.deleteById(id);
	}

}