package br.com.geninho.enuns;

public enum Role {

	USER, ADM;

	public String getRoleName() {
		return this.name();
	}
}
