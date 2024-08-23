package com.senai.apimuralvagas.models;

import jakarta.persistence.*;


@Entity
@Table(name = "empresa")
public class EmpresaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id")
	private int empresaId;
	private String nome;
	
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
	private Boolean autorizacao;
	
	
	public int getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Boolean getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(Boolean autorizacao) {
		this.autorizacao = autorizacao;
	}
	
	

	
	
}
