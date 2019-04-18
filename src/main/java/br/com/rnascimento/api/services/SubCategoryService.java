package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.SubCategoryDTO;
import br.com.rnascimento.api.entities.SubCategory;
import br.com.rnascimento.api.repositories.SubCategoryRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class SubCategoryService {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	/**
	 * Find all SubCategory.
	 * 
	 * @return List<CategoryDTO>
	 */
	public List<SubCategoryDTO> findAll() {
		List<SubCategory> listSubCategory = this.subCategoryRepository.findAll();
		if(listSubCategory.isEmpty() || listSubCategory == null) {
			return new ArrayList<SubCategoryDTO>();
		}
		return ModelMapperUtil.converter(listSubCategory, SubCategoryDTO.class);
	}

	/**
	 * Save a new SubCategory or Update an existing SubCategory. 
	 * 
	 * @param subCategoryDTO
	 * @return SubCategoryDTO
	 */
	public SubCategoryDTO saveOrUpdate(@Valid SubCategoryDTO subCategoryDTO) {
		SubCategory subCategory = ModelMapperUtil.converter(subCategoryDTO, SubCategory.class);
		subCategory = this.subCategoryRepository.save(subCategory);
		return ModelMapperUtil.converter(subCategory, SubCategoryDTO.class);
	}

	/**
	 * Find a SubCategory.
	 * 
	 * @param id
	 * @return SubCategoryDTO
	 */
	public SubCategoryDTO findById(Long id) {
		SubCategoryDTO userDTO = new SubCategoryDTO();
		Optional<SubCategory> user = this.subCategoryRepository.findById(id);
		if(user.isPresent()) {
			userDTO = ModelMapperUtil.converter(user.get(), SubCategoryDTO.class);
		}
		return userDTO;
	}

	/**
	 * Delete a SubCategory.
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		this.subCategoryRepository.deleteById(id);
	}
}