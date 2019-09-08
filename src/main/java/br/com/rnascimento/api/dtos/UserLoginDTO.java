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
public class UserLoginDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5251291976474751439L;

	@NotBlank(message = "The login can not is empty.")
	private String login;
	
	@NotBlank(message = "The password can not is empty.")
	@Size(min = 6, max = 99, message = "password must be between 6 and 99")
	private String password;

}