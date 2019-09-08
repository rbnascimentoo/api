package br.com.rnascimento.api.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7782757945886557726L;
	
	private int value;
	private String message;

}
