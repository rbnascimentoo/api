package br.com.rnascimento.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "subCategory")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SubCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1536538244004989419L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 255)
	@NotEmpty(message = "The name can not is empty.")
	private String name;
	
	@JoinColumn(name = "idCategory")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
}