package br.com.geninho.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.geninho.dto.UserDTO;
import br.com.geninho.dto.UserInsertDTO;
import br.com.geninho.entities.User;
import br.com.geninho.exception.CpfException;
import br.com.geninho.exception.EmailException;
import br.com.geninho.exception.PasswordException;
import br.com.geninho.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}

	public UserDTO createUser(UserInsertDTO userInsert) {
		
		if (!userInsert.getPassword().equals(userInsert.getConfirmPassword())) {
			throw new PasswordException("As Senhas não conferem");
		}
		if (userRepository.findByEmail(userInsert.getEmail())!= null) {
			throw new EmailException("Já existe um usuário com este email");
		}
		if (userRepository.findByCpf(userInsert.getCpf()) != null) {
			throw new CpfException("Já existe um usuário com este CPF");
		}

		User user = new User();
		user.setName(userInsert.getName());
		user.setEmail(userInsert.getEmail());
		user.setCpf(userInsert.getCpf());
		user.setProfile(userInsert.getProfile());
		user.setPassword(userInsert.getPassword());

		userRepository.save(user);
		UserDTO userDTO = new UserDTO(user);
		
		return userDTO;
	}
}
