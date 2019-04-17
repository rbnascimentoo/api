package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.repositories.UserRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll() {
		List<User> listUser = this.userRepository.findAll();
		if(listUser.isEmpty() || listUser == null) {
			return new ArrayList<UserDTO>();
		}
		return ModelMapperUtil.converter(listUser, UserDTO.class);
	}

	public UserDTO saveOrUpdate(UserDTO userDto) {
		User user = ModelMapperUtil.converter(userDto, User.class);
		user = this.userRepository.save(user);
		return ModelMapperUtil.converter(user, UserDTO.class);
	} 

	public UserDTO findById(Long id) {
		UserDTO userDTO = new UserDTO();
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			userDTO = ModelMapperUtil.converter(user.get(), UserDTO.class);
		}
		return userDTO;
	}

	public void deleteById(Long id) {
		this.userRepository.deleteById(id);
	}
}