package br.com.rnascimento.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ExpenseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1246469146218752652L;
	
	private Long id;
	private BigDecimal amount;
	private String description;
	private CategoryDTO category;
	private UserDTO user;
	
}