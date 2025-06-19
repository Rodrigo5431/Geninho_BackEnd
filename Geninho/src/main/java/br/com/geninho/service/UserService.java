package br.com.geninho.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.geninho.dto.UserDTO;
import br.com.geninho.dto.UserInsertDTO;
import br.com.geninho.entities.User;
import br.com.geninho.exception.CpfException;
import br.com.geninho.exception.EmailException;
import br.com.geninho.exception.NotFoundException;
import br.com.geninho.exception.PasswordException;
import br.com.geninho.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}

	public UserDTO getUserById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isEmpty()) {
			throw new NotFoundException("Usuário não encontrado");
		}

		return new UserDTO(userOpt.get());
	}

	public UserDTO createUser(UserInsertDTO userInsert) {

		if (!userInsert.getPassword().equals(userInsert.getConfirmPassword())) {
			throw new PasswordException("As Senhas não conferem");
		}
		if (userRepository.findByEmail(userInsert.getEmail()) != null) {
			throw new EmailException("Já existe um usuário com este email");
		}
		if (userRepository.findByCpf(userInsert.getCpf()) != null) {
			throw new CpfException("Este CPF já está cadastrado");
		}

		User user = new User();
		user.setName(userInsert.getName());
		user.setEmail(userInsert.getEmail());
		user.setCpf(userInsert.getCpf());
		user.setProfile(userInsert.getProfile());
		user.setPassword(encoder.encode(userInsert.getPassword()));

		userRepository.save(user);
		UserDTO userDTO = new UserDTO(user);

		return userDTO;
	}

	public UserDTO updateUser(Long id, UserInsertDTO userInsert) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new NotFoundException("Usúario não encontrado");
		}
		if (!userInsert.getPassword().equals(userInsert.getConfirmPassword())) {
			throw new PasswordException("As Senhas não Conferem");
		}
		if (userRepository.findByEmail(userInsert.getEmail()) != null) {
			throw new EmailException("Este email ja existe");
		}
		if (userRepository.findByCpf(userInsert.getEmail()) != null) {
			throw new EmailException("Este cpf ja existe");
		}

		User user = new User();
		user.setId(id);
		user.setName(userInsert.getName() != null ? userInsert.getName() : userOpt.get().getName());
		user.setCpf(userInsert.getCpf() != null ? userInsert.getCpf() : userOpt.get().getCpf());
		user.setEmail(userInsert.getEmail() != null ? userInsert.getEmail() : userOpt.get().getEmail());
		user.setPassword(userInsert.getPassword() != null ? encoder.encode(userInsert.getPassword()) : userOpt.get().getPassword());
		user.setProfile(userInsert.getProfile() != null ? userInsert.getProfile() : userOpt.get().getProfile());
		userRepository.save(user);

		return new UserDTO(user);

	}

	public String deleteUser(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new NotFoundException("Usúario não encontrado");
		}
		userRepository.deleteById(id);
		return "Usuário deletado com sucesso!";
	}
}
