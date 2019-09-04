package br.com.rnascimento.api.dtos;

import java.io.Serializable;

import br.com.rnascimento.api.enums.Role;
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
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7827518361373952276L;

	private Long id;
	private String name;
	private String login;
	private String password;
	private Role role;

}