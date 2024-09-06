package com.senai.apimuralvagas.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "empresa")
@Data
public class EmpresaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id", updatable = false, nullable = false)	
	private int empresaId;
	@Column(name = "nome_empresa")
	private String nomeEmpresa;
	private String senha;
	@OneToOne
	@JoinColumn(name = "logo_id")
	@JsonIgnore
	private LogoModel logo;
	private String cnpj;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
	@JsonIgnore
	private EnderecoModel endereco;
    @OneToOne
    @JoinColumn(name = "descricao_id")
	@JsonIgnore
    private DescricaoEmpresaModel descricao;
	private String email;
	private String telefone; 
	private Boolean autorizacao;
    @OneToMany(mappedBy = "empresaId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnore
    private Set<EmpresaVagaModel> empresaVagas;

}
