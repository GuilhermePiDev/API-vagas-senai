package com.senai.apimuralvagas.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Table(name = "empresa")
@Data
public class EmpresaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id", updatable = false, nullable = false)	
	private int empresaId;

	@NotBlank
	private String nomeEmpresa;

	@NotBlank
	private String senha;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "logo_id" )
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

    @OneToMany(mappedBy = "empresaId", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
    private Set<EmpresaVagaModel> empresaVagas;

}