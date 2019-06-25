package br.com.rnascimento.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
	
	private T data;
	
	private List<String> erros;
	
	public List<String> getErros() {
		if(this.erros == null) {
			this.erros = new ArrayList<String>();
		}
		return this.erros;
	}
	
}