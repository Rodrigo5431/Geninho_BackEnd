package br.com.geninho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geninho.dto.PasswordChangeDTO;
import br.com.geninho.service.PasswordResetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private PasswordResetService passwordResetService;

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@Valid @RequestBody PasswordResetRequest request) {
		return ResponseEntity.ok(passwordResetService.requestPasswordReset(request.getEmail()));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
		passwordResetService.changePassword(passwordChangeDTO);
		return ResponseEntity.ok("Senha redefinida com sucesso.");
	}

	public static class PasswordResetRequest {
		@Email(message = "Forneça um email válido")
		@NotBlank
		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}