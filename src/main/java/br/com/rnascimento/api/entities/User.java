package br.com.rnascimento.api.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity
@Data
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
	
	@Column(nullable = false)
	@NotEmpty(message = "The name can not is empty.")
	private String name;
	
	@Column(nullable = false)
	private LocalDate createDate;
	
	@Column(nullable = false)
	private LocalDate updateDate;
	
	@PrePersist
	public void prePersist() {
		this.createDate = LocalDate.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updateDate = LocalDate.now();
	}
}