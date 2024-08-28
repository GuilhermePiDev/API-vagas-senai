package com.senai.apimuralvagas.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name = "empresa")
public class EmpresaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id")
	private int empresaId;
	private String nomeEmpresa;
	private String senha;
	
	@OneToOne
	@JoinColumn(name = "logo_id")
	private LogoModel logo;
	private String cnpj;
	
    @ManyToOne
    @JoinColumn(name = "endereco_id")
	private EnderecoModel endereco;
    
    @OneToOne
    @JoinColumn(name = "descricao_id")
    private DescricaoEmpresaModel descricao;
	private String email;
	private String telefone; 
	private Boolean autorizacao;
	
    @OneToMany(mappedBy = "empresaId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
    private Set<EmpresaVagaModel> empresaVagas;

	public int getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}

	public String getNome() {
		return nomeEmpresa;
	}

	public void setNome(String nome) {
		this.nomeEmpresa = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LogoModel getLogo() {
		return logo;
	}

	public void setLogo(LogoModel logo) {
		this.logo = logo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public DescricaoEmpresaModel getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoEmpresaModel descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Boolean autorizacao) {
		this.autorizacao = autorizacao;
	}

	public Set<EmpresaVagaModel> getEmpresaVagas() {
		return empresaVagas;
	}

	public void setEmpresaVagas(Set<EmpresaVagaModel> empresaVagas) {
		this.empresaVagas = empresaVagas;
	}
	
	
	
	
	

	
	
}
