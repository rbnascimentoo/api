package br.com.rnascimento.api.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSaveUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143514279112643918L;
	
	@NotBlank(message = "The name can not is empty.")
	private String name;
	
	@NotBlank(message = "The login can not is empty.")
	private String login;

	@NotBlank(message = "The password can not is empty.")
	private String password;
	
}