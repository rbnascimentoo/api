package br.com.rnascimento.api.resources;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private UserService userService;

	@Test
	public void findAllUsers() {
		List<UserDTO> userDTOList = Arrays.asList(UserDTO.builder().id(1L).build(), UserDTO.builder().id(2L).build());
		BDDMockito.when(this.userService.findAll()).thenReturn(userDTOList);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/", String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	@Test
	public void findUserById() {
		Long id = 1L;
		UserDTO userDTO = UserDTO.builder().id(id).build();
		BDDMockito.when(this.userService.findById(id)).thenReturn(userDTO);
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/" + id, String.class);
		
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}
	
}