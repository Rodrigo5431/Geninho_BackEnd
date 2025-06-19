package br.com.geninho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geninho.dto.UserDTO;
import br.com.geninho.dto.UserInsertDTO;
import br.com.geninho.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ADM')")
	@GetMapping
	public List<UserDTO>getUsers() {
		return userService.getAllUsers();
	}
	@PreAuthorize("hasAnyRole('ADM', 'USER')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO userDto = userService.getUserById(id);
		return ResponseEntity.ok(userDto);
	}
	@PreAuthorize("hasAnyRole('ADM', 'USER')")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		UserDTO userDto = userService.createUser(userInsertDTO);
		return ResponseEntity.ok(userDto);
		
	}
	@PreAuthorize("hasAnyRole('ADM', 'USER')")
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserInsertDTO userInsertDTO){
		UserDTO userDto = userService.updateUser(id, userInsertDTO);
		
		return ResponseEntity.ok(userDto);
	}
	@PreAuthorize("hasAnyRole('ADM', 'USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	
}
