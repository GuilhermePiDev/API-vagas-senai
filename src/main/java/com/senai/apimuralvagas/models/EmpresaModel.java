package com.senai.apimuralvagas.models;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "empresa")
@Data
public class EmpresaModel implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id", updatable = false, nullable = false)
	private int empresaId;

	@NotBlank
	private String nomeEmpresa;

	@NotBlank
	private String senha;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "logo_id")
	@Valid
	private LogoModel logo;

	@NotBlank
	@Size(min = 14, max = 18)
	private String cnpj;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@Valid
	private EnderecoModel endereco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "descricao_id")
	@Valid
	private DescricaoEmpresaModel descricao;

	@NotBlank
	@Email
	private String email;

	private String telefone;

	private Boolean autorizacao = false;

	private RoleEnum role = RoleEnum.EMPRESABLOCK;

	@OneToMany(mappedBy = "empresaId", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private Set<EmpresaVagaModel> empresaVagas;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == role.EMPRESA)
			return List.of(new SimpleGrantedAuthority("ROLE_EMPRESABLOCK"), new SimpleGrantedAuthority("ROLE_EMPRESA"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_EMPRESABLOCK"));
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