package br.com.rnascimento.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rnascimento.api.entities.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM user u WHERE u.login = ?1 AND u.password = ?2 AND u.state LIKE 'ACTIVE' ")
	public Optional<User> login(String login, String password);

}