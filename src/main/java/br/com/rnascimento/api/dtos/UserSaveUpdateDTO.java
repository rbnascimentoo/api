package br.com.rnascimento.api.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSaveUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143514279112643918L;
	
	@NotBlank(message = "The name can not is empty.")
	private String name;
	
	@NotBlank(message = "The login can not is empty.")
	private String login;
	
	@NotBlank
	@Size(min = 6, max = 99, message = "password must be between 6 and 99")
	private String password;
	
}