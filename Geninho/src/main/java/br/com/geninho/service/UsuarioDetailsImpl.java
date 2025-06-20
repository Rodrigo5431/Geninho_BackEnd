package br.com.geninho.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.geninho.entities.User;
import br.com.geninho.repository.UserRepository;

@Service
public class UsuarioDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByEmail(username);

        if (usuario.isEmpty()) {
        	throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return usuario.get();
    }
}