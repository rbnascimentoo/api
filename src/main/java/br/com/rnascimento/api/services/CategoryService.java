package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.CategoryDTO;
import br.com.rnascimento.api.entities.Category;
import br.com.rnascimento.api.repositories.CategoryRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Find all Category.
	 * 
	 * @return List<CategoryDTO>
	 */
	public List<CategoryDTO> findAll() {
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
	public CategoryDTO saveOrUpdate(@Valid CategoryDTO categoryDTO) {
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
	public CategoryDTO findById(Long id) {
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
	public void deleteById(Long id) {
		this.categoryRepository.deleteById(id);
	}

}