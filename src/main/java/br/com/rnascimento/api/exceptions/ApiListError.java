package br.com.rnascimento.api.exceptions;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiListError implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8491632734154490350L;
	
	private int value;
	private String message;
	private List<String> fields;

}
