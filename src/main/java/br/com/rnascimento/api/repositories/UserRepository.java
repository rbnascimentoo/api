package br.com.rnascimento.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rnascimento.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}