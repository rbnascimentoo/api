package br.com.rnascimento.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.rnascimento.api.entities.User;
import br.com.rnascimento.api.repositories.UserRepository;
import javassist.NotFoundException;

@Component(value = "accessManager")
public class AccessManager {
	
	@Autowired
	private UserRepository userRepository;

	public boolean isOwner(Long id) throws NotFoundException {
		String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Optional<User> user = this.userRepository.findByLogin(login);
		
		if(!user.isPresent()) {
			throw new NotFoundException("There are not user with login = " + login);
		}
		
		return user.get().getId().equals(id);
	}
	
}