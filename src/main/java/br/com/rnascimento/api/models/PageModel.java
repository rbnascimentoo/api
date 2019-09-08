package br.com.rnascimento.api.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7629470400194336858L;
	
	private int totalElements;
	private int pageSize;
	private int totalPages;
	private List<T> elements;
	
}
