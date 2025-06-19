package br.com.geninho.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordChangeDTO {

    @NotBlank(message = "O token não pode estar em branco")
    private String token;

    @NotBlank(message = "A nova senha não pode estar em branco")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}