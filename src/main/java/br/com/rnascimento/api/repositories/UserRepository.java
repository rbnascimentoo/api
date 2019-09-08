package br.com.rnascimento.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM user u WHERE u.login = ?1 AND u.password = ?2 AND u.state LIKE 'ACTIVE' ")
	public Optional<User> login(String login, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE user SET role = ?2 WHERE id = ?1")
	public int updateRole(Long id, Role role);
	
	public Optional<User> findByLogin(String login);

}