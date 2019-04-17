package br.com.rnascimento.api.dtos;

import java.io.Serializable;

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
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7827518361373952276L;
	
	private Long id;
	private String name;
//	private LocalDate createDate;
//	private LocalDate updateDate;
	
}