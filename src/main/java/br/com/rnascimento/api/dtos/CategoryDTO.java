package br.com.rnascimento.api.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 243771946141135682L;
	
	private Long id;
	private String name;
	private List<SubCategoryDTO> listSubCategory;
}