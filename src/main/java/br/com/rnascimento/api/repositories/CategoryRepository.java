package br.com.rnascimento.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rnascimento.api.entities.Category;

@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
