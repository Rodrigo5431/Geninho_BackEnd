package br.com.geninho.dto;

import br.com.geninho.entities.User;
import br.com.geninho.enuns.Role;

public class UserDTO {

	private String name;
	private String email;
	private String cpf;
	private Role profile;

	public UserDTO(String name, String email, String cpf, Role profile) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.profile = profile;
	}
	public UserDTO(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.profile = user.getProfile();
	}

	public UserDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Role getProfile() {
		return profile;
	}

	public void setProfile(Role profile) {
		this.profile = profile;
	}

}
