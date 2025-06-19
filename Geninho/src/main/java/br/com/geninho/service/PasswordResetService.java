package br.com.geninho.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import br.com.geninho.dto.PasswordChangeDTO; 
import br.com.geninho.entities.PasswordResetToken;
import br.com.geninho.entities.User;
import br.com.geninho.exception.IncorrectCodeException;
import br.com.geninho.exception.NotFoundException;
import br.com.geninho.repository.PasswordResetTokenRepository;
import br.com.geninho.repository.UserRepository;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder; 

	@Transactional
	public String requestPasswordReset(String userEmail) {
		User user = userRepository.findByEmail(userEmail)
				.orElseThrow(() -> new NotFoundException("Usuário não encontrado com o email: " + userEmail));

		tokenRepository.findByUserId(user.getId()).ifPresent(tokenRepository::delete);

		SecureRandom random = new SecureRandom();
		int code = 100000 + random.nextInt(900000);
		String tokenCode = String.valueOf(code);

		PasswordResetToken resetToken = new PasswordResetToken(tokenCode, user);
		tokenRepository.save(resetToken);

		return emailService.sendPasswordResetCodeEmail(user, tokenCode);
	}
	
	@Transactional
	public void changePassword(PasswordChangeDTO dto) {
		PasswordResetToken token = tokenRepository.findByToken(dto.getToken())
			.orElseThrow(() -> new IncorrectCodeException("Token inválido ou não encontrado."));

		if (token.isExpired()) {
			tokenRepository.delete(token);
			throw new IncorrectCodeException("Seu token de redefinição de senha expirou. Por favor, solicite um novo.");
		}

		User user = token.getUser();
		
		String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);

		tokenRepository.delete(token);
	}
}