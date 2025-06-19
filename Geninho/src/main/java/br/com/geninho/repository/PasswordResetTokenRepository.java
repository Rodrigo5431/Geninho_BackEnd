package br.com.geninho.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.geninho.entities.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	Optional<PasswordResetToken> findByToken(String token);

	Optional<PasswordResetToken> findByUserId(Long userId);
}