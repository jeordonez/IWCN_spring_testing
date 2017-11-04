package com.master.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity

public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	private String user;
	private String pass;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> roles;
	

	public Usuario() {
	}
	public Usuario(String user, String password, List<GrantedAuthority> roles) {
		this.user = user;
		this.pass = new BCryptPasswordEncoder().encode(password);
		this.roles = roles;
	}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<GrantedAuthority> getRoles() {
		return roles;
	}
	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
	

}
