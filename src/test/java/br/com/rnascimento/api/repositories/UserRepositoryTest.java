package br.com.rnascimento.api.repositories;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.enums.Role;
import br.com.rnascimento.api.enums.State;
import br.com.rnascimento.api.utils.HashUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveUser() {
		
		String passwordHash = HashUtil.getSecureHash("admin12345");
		
		User user = new User(null, "Administrator", "administrator", passwordHash, Role.ADMINISTRATOR, 
				null, null, null);

		Optional<User> userDB = this.userRepository.findById(1L);

		if(userDB.isPresent()) {
			Assert.assertThat(user, CoreMatchers.notNullValue());
		}
		else {
			User createUser = this.userRepository.save(user);

			Assert.assertThat(createUser.getId(), CoreMatchers.notNullValue());
		}
	}
	
	@Test
	public void updateUser() {
		
		String passwordHash = HashUtil.getSecureHash("admin12345");
		
		User user = new User(1L, "Administrator", "admin", passwordHash, Role.ADMINISTRATOR, 
				new Date(), new Date(), State.ACTIVE);
		User createUser = this.userRepository.save(user);
		
		Assert.assertThat(createUser.getId(), CoreMatchers.notNullValue());
	}
	
	@Test
	public void findUserById() {
		Optional<User> user = this.userRepository.findById(1L);
		
		Assert.assertThat(user, CoreMatchers.notNullValue());
	}

	@Test
	public void findALlUsers() {
		List<User> users = this.userRepository.findAll();
		
		Assert.assertThat(users, CoreMatchers.notNullValue());
	}
	
	@Test
	public void findUserByLogin() {
		Optional<User> user = this.userRepository.login("Administrator", "admin123");
		
		Assert.assertThat(user, CoreMatchers.notNullValue());
	}
	
	@Test
	public void updateRole() {
		int value = this.userRepository.updateRole(1L, Role.ADMINISTRATOR);
		
		assertThat(value).isEqualTo(1);
	}

}