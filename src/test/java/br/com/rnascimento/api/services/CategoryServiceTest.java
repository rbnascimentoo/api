package br.com.rnascimento.api.services;

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

import br.com.rnascimento.api.dtos.CategoryDTO;
import br.com.rnascimento.api.entities.Category;
import br.com.rnascimento.api.repositories.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService categorySevice;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Test
	public void findAllCategorys() {
		
		Mockito.when(this.categoryRepository.findAll()).thenReturn(Arrays.asList(Category.builder().id(1L).name("category Test").build()));
		
		List<CategoryDTO> listCategoryDTO = this.categorySevice.findAll();
		
		Mockito.verify(this.categoryRepository).findAll();
		
		Assert.assertThat(listCategoryDTO, CoreMatchers.notNullValue());
		
	}
	
	@Test
	public void saveOrUpdateCategorys() {
		
		Category category = Category.builder().build();
		
		Mockito.when(this.categoryRepository.save(category)).thenReturn(Category.builder().id(1L).build());
		
		CategoryDTO categoryDTOReturn = this.categorySevice.saveOrUpdate(CategoryDTO.builder().build());
		
		Mockito.verify(this.categoryRepository).save(category);
		
		Assert.assertThat(categoryDTOReturn.getId(), CoreMatchers.notNullValue());
	}
	
	@Test
	public void findCategory() {

		Optional<Category> category = Optional.of(Category.builder().id(1L).build());
		
		Mockito.when(this.categoryRepository.findById(1L)).thenReturn(category);
		
		CategoryDTO categoryDTO = this.categorySevice.findById(1L);
		
		Mockito.verify(this.categoryRepository).findById(1L);
		
		Assert.assertThat(categoryDTO, CoreMatchers.notNullValue());
		
	}
	
	@Test
	public void deleteCategory() {

		Category category = Category.builder().id(1L).build();

		this.categorySevice.deleteById(1L);

		Mockito.verify(this.categoryRepository).deleteById(category.getId());
	}

}