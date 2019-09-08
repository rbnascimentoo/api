package br.com.rnascimento.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rnascimento.api.dtos.UserDTO;
import br.com.rnascimento.api.dtos.UserSaveUpdateDTO;
import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.models.PageModel;
import br.com.rnascimento.api.models.PageRequestModel;
import br.com.rnascimento.api.repositories.UserRepository;
import br.com.rnascimento.api.utils.HashUtil;
import br.com.rnascimento.api.utils.ModelMapperUtil;

@Service
public class UserService implements UserDetailsService {
	
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
	 * Save a new User. 
	 * 
	 * @param usuarioSaveDTO
	 * @return UserDTO
	 */
	@CacheEvict(allEntries = true, value = {"findAllUser", "findByIdUser"}, beforeInvocation = true)
	public UserDTO save(UserSaveUpdateDTO userSaveDTO) {
		LOG.info("### Saving a new User or Updating an existing User... ###");
		User user = ModelMapperUtil.converter(userSaveDTO, User.class);
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		user = this.userRepository.save(user);
		return ModelMapperUtil.converter(user, UserDTO.class);
	}
	
	/**
	 * Update an existing User. 
	 * 
	 * @param usuarioUpdateDTO
	 * @return UserDTO
	 */
	@CacheEvict(allEntries = true, value = {"findAllUser", "findByIdUser"}, beforeInvocation = true)
	public UserDTO update(Long id, UserSaveUpdateDTO userUpdateDTO) {
		LOG.info("### Saving a new User or Updating an existing User... ###");
		User user = ModelMapperUtil.converter(userUpdateDTO, User.class);
		user.setId(id);
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
	
	/**
	 * Find user login.
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
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
	
	/**
	 * 
	 * 
	 * @param pageRequestModel
	 * @return
	 */
	@Cacheable("findAllUserPaginator")
	public PageModel<UserDTO> listAllUserPaginator(PageRequestModel pageRequestModel){
		LOG.info("### Find a User Paginator... ###");
		PageRequest pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
		Page<User> page = this.userRepository.findAll(pageable);
		PageModel<UserDTO> pageModel = new PageModel<>((int) page.getTotalElements(), 
				page.getSize(), page.getTotalPages(), ModelMapperUtil.converter(page.getContent(), UserDTO.class));
		return pageModel;
	}
	
	/**
	 * 
	 * @param userDto
	 * @return 0
	 */
	@CacheEvict(allEntries = true, value = {"findAllUser", "findByIdUser"}, beforeInvocation = true)
	public int updateUserRole(UserDTO userDto) {
		return this.userRepository.updateRole(userDto.getId(), userDto.getRole());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> result = this.userRepository.findByLogin(username);
		
		if(!result.isPresent()) {
			throw new UsernameNotFoundException("Dosen't exist user " + username);
		}
		
		User user = result.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE " + user.getRole().name()));
		
		org.springframework.security.core.userdetails.User userSecurity = 
				new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
		
		return userSecurity;
	}
	
}