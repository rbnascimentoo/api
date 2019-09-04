package br.com.rnascimento.api.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.enums.Role;
import br.com.rnascimento.api.enums.State;
import br.com.rnascimento.api.repositories.UserRepository;
import br.com.rnascimento.api.utils.HashUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userSevice;

	@Mock
	private UserRepository userRepository;

	@Test
	public void findAllUsers() {

		Mockito.when(this.userRepository.findAll())
				.thenReturn(Arrays.asList(User.builder().id(1L).name("User Test").build()));

		List<UserDTO> listUserDTO = this.userSevice.findAll();

		Mockito.verify(this.userRepository).findAll();

		Assert.assertThat(listUserDTO, CoreMatchers.notNullValue());

	}

	@Test
	public void updateUsers() {

		String password = "password";
		String passwordHash = HashUtil.getSecureHash(password);

		User userCreation = User.builder().name("User Test").login("login").password(passwordHash)
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		User userCreated = User.builder().id(1L).name("User Test").login("login").password(passwordHash)
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		UserDTO userDto = UserDTO.builder().name("User Test").login("login").password("password")
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		User userUpdate = User.builder().id(1L).name("User updated").login("login").password(passwordHash)
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();
		UserDTO userUpdateDto = UserDTO.builder().id(1L).name("User updated").login("login").password("password")
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		Mockito.when(this.userRepository.save(userCreation)).thenReturn(userCreated);
		UserDTO userDTOReturn = this.userSevice.saveOrUpdate(userDto);
		Mockito.verify(this.userRepository).save(userCreation);

		Mockito.when(this.userRepository.save(userUpdate)).thenReturn(userUpdate);
		UserDTO userUpdateDTOReturn = this.userSevice.saveOrUpdate(userUpdateDto);
		Mockito.verify(this.userRepository).save(userUpdate);

		Assert.assertTrue(userUpdateDTOReturn.getName().equals(userUpdate.getName()));
	}

	@Test
	public void saveUsers() {

		String password = "password";
		String passwordHash = HashUtil.getSecureHash(password);

		User userCreation = User.builder().name("User Test").login("login").password(passwordHash)
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		User userCreated = User.builder().id(1L).name("User Test").login("login").password(passwordHash)
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		UserDTO userDto = UserDTO.builder().name("User Test").login("login").password("password")
				.role(Role.ADMINISTRATOR).state(State.ACTIVE).build();

		Mockito.when(this.userRepository.save(userCreation)).thenReturn(userCreated);

		UserDTO userDTOReturn = this.userSevice.saveOrUpdate(userDto);

		Mockito.verify(this.userRepository).save(userCreation);

		Assert.assertThat(userDTOReturn.getId(), CoreMatchers.notNullValue());
	}

	@Test
	public void findUser() {

		Optional<User> user = Optional.of(User.builder().id(1L).name("User Test").build());

		Mockito.when(this.userRepository.findById(1L)).thenReturn(user);

		UserDTO userDTO = this.userSevice.findById(1L);

		Mockito.verify(this.userRepository).findById(1L);

		Assert.assertThat(userDTO, CoreMatchers.notNullValue());

	}

	@Test
	public void deleteUser() {

		User user = User.builder().id(1L).name("User Test").build();

		this.userSevice.deleteById(1L);

		Mockito.verify(this.userRepository).deleteById(user.getId());
	}

	@Test
	public void findUserByLogin() {

		String login = "admin";
		String password = "admin";
		String passwordHash = HashUtil.getSecureHash(password);

		Optional<User> user = Optional
				.of(User.builder().id(1L).name("User Test").login(login).password(passwordHash).build());

		Mockito.when(this.userRepository.login(login, password)).thenReturn(user);

		UserDTO userDTO = this.userSevice.login(login, password);

		Mockito.verify(this.userRepository).login(login, passwordHash);

		Assert.assertThat(userDTO, CoreMatchers.notNullValue());

	}

}