package com.senai.apimuralvagas.models;

import jakarta.persistence.*;


@Entity
@Table(name = "empresa")
public class EmpresaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empresaId;
	private String nome;
	private String cnpj;
	
    @ManyToOne
    @MapsId("enderecoid")
    @JoinColumn(name = "enderecoid")
	private EnderecoModel endereco;
    
	private String ramo;
	private String site;
	private int qtdFuncionarios;
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
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getQtdFuncionarios() {
		return qtdFuncionarios;
	}
	public void setQtdFuncionarios(int qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}
	public Boolean getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(Boolean autorizacao) {
		this.autorizacao = autorizacao;
	}
	
	
}
