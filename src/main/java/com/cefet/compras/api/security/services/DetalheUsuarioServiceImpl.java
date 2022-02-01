package com.cefet.compras.api.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cefet.compras.api.entities.Usuario;
import com.cefet.compras.api.repositories.UsuarioRepository;
import com.cefet.compras.api.security.data.DetalheUsuarioData;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{
	
    private UsuarioRepository usuarioRepository;

    public DetalheUsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
	        if (usuario.isEmpty()) {
	        	System.out.println("Usuário [" + email + "] não encontrado");
	            throw new UsernameNotFoundException("Usuário [" + email + "] não encontrado");
	        }

	        return new DetalheUsuarioData(usuario);
	}

}
