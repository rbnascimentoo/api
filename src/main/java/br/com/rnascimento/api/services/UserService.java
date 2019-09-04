package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.repositories.UserRepository;
import br.com.rnascimento.api.utils.HashUtil;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class UserService {
	
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Find all User.
	 * 
	 * @return List<UserDTO>
	 */
	@Cacheable("findAllUser")
	public List<UserDTO> findAll() {
		LOG.info("### Find all User... ###");
		List<User> listUser = this.userRepository.findAll();
		if(listUser.isEmpty() || listUser == null) {
			return new ArrayList<UserDTO>();
		}
		return ModelMapperUtil.converter(listUser, UserDTO.class);
	}

	/**
	 * Save a new User or Update an existing User. 
	 * 
	 * @param userDto
	 * @return UserDTO
	 */
	@CacheEvict(allEntries = true, value = {"findAllUser", "findByIdUser"}, beforeInvocation = true)
	public UserDTO saveOrUpdate(UserDTO userDto) {
		LOG.info("### Saving a new User or Updating an existing User... ###");
		User user = ModelMapperUtil.converter(userDto, User.class);
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		user = this.userRepository.save(user);
		return ModelMapperUtil.converter(user, UserDTO.class);
	} 

	/**
	 * Find a User.
	 * 
	 * @param id
	 * @return UserDTO
	 */
	@Cacheable("findByIdUser")
	public UserDTO findById(Long id) {
		LOG.info("### Find a User... ###");
		UserDTO userDTO = new UserDTO();
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			userDTO = ModelMapperUtil.converter(user.get(), UserDTO.class);
		}
		return userDTO;
	}

	/**
	 * Delete a User.
	 * 
	 * @param id
	 */
	@CacheEvict(allEntries = true, value = {"findAllUser", "findByIdUser"}, beforeInvocation = true)
	public void deleteById(Long id) {
		LOG.info("### Deleting a User... ###");
		this.userRepository.deleteById(id);
	}
	
	public UserDTO login(String login, String password) {
		LOG.info("### Find a User... ###");
		UserDTO userDTO = new UserDTO();
		
		password = HashUtil.getSecureHash(password);
		
		Optional<User> user = this.userRepository.login(login, password);
		if(user.isPresent()) {
			userDTO = ModelMapperUtil.converter(user.get(), UserDTO.class);
		}
		return userDTO;
	}
}