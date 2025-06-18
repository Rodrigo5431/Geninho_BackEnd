package br.com.geninho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping
	public List<UserDTO>getUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		UserDTO userDto = userService.createUser(userInsertDTO);
		return ResponseEntity.ok(userDto);
		
	}
}
