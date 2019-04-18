package br.com.rnascimento.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rnascimento.api.entities.Expense;

@Repository
@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}