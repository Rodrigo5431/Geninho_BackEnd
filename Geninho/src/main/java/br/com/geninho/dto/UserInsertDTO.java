package br.com.geninho.dto;

import br.com.geninho.enuns.Role;

public class UserInsertDTO {

	private String name;
	private String email;
	private String cpf;
	private Role profile;
	private String password;
	private String confirmPassword;

	public UserInsertDTO(String name, String email, String cpf, Role profile, String password, String confirmPassword) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.profile = profile;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public UserInsertDTO() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
