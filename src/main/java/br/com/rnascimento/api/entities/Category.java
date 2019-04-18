package br.com.rnascimento.api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "category")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686541270955484170L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 255)
	@NotEmpty(message = "The name can not is empty.")
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<SubCategory> listSubCategory;
	
}