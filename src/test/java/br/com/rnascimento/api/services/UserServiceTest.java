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
import br.com.rnascimento.api.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userSevice;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void findAllUsers() {
		
		Mockito.when(this.userRepository.findAll()).thenReturn(Arrays.asList(User.builder().id(1L).name("User Test").build()));
		
		List<UserDTO> listUserDTO = this.userSevice.findAll();
		
		Mockito.verify(this.userRepository).findAll();
		
		Assert.assertThat(listUserDTO, CoreMatchers.notNullValue());
		
	}
	
	@Test
	public void saveOrUpdateUsers() {
		
		User user = User.builder().name("User Test").build();
		
		Mockito.when(this.userRepository.save(user)).thenReturn(User.builder().id(1L).name("User Test").build());
		
		UserDTO userDTOReturn = this.userSevice.saveOrUpdate(UserDTO.builder().name("User Test").build());
		
		Mockito.verify(this.userRepository).save(user);
		
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

}