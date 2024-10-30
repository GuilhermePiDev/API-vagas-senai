package com.senai.apimuralvagas.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminModel implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;

	@NotBlank()
	private String nome;

	@NotBlank()
	private String senha;

	@NotBlank
	@Size(min = 11, max = 14)
	private String cpf;

	@NotBlank()
	@Email()
	private String email;


	private RoleEnum role = RoleEnum.ADMIN;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_EMPRESA"));

	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
