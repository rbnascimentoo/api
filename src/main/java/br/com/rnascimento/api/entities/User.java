package br.com.rnascimento.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import br.com.rnascimento.api.enums.Role;
import br.com.rnascimento.api.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1991236981285911306L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 255)
	@NotEmpty(message = "The name can not is empty.")
	private String name;
	
	@Column(nullable = false, length = 30, unique =  true)
	@NotEmpty(message = "The login can not is empty.")
	private String login;
	
	@Column(nullable = false, length = 75)
	@NotEmpty(message = "The password can not is empty.")
	private String password;
	
	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@Column(name = "date_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;
	
	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private State state;
	
	@PrePersist
	public void prePersist() {
		this.dateCreation = new Date();
		this.state = State.ACTIVE;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.dateUpdate = new Date();
	}
	
}