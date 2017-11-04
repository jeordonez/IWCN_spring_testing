package com.master.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.master.models.Usuario;
import com.master.repositories.UsuarioRepositorio;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioRepositorio usrepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		Usuario user = usrepo.findByUser(username);  
		if (user == null) {
			throw new BadCredentialsException("User not found");
		}
		if (!new BCryptPasswordEncoder().matches(password, user.getPass())) {  
			throw new BadCredentialsException("Wrong password");
		}
		List<GrantedAuthority> roles = user.getRoles();
		return new UsernamePasswordAuthenticationToken(username, password, roles);	
		}

	@Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}

