package br.com.geninho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.geninho.entities.User;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    
    public String sendPasswordResetCodeEmail(User user, String code) {
        try {
            String mensagem = String.format(
                "Olá %s,\n\n" +
                "Recebemos uma solicitação para redefinir a senha da sua conta.\n\n" +
                "🔐 Seu código de verificação é: %s\n\n" +
                "Por favor, insira este código na página de recuperação de senha para continuar.\n\n" +
                "Se você não solicitou essa alteração, pode ignorar este e-mail com segurança.\n\n" +
                "Este código expira em 15 minutos.\n\n" +
                "Atenciosamente,\n" +
                "Equipe Geninho",
                user.getName(),
                code
            );

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromAddress);
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Recuperação de Senha - Código de Verificação");
            mailMessage.setText(mensagem);

            javaMailSender.send(mailMessage);
            
            return "Email enviado com sucesso";

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail com código de recuperação.", e);
        }
    }
}