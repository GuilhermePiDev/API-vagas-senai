package com.senai.apimuralvagas.models;

import jakarta.persistence.*;


@Entity
@Table(name = "endereco")
public class EnderecoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_id")
	private int enderecoid;
	private String cep;
	private String rua;
	private String bairro;
	private String numero;
	private String cidade;
	private String pais;
	
	
	public int getEnderecoid() {
		return enderecoid;
	}
	public void setEnderecoid(int enderecoid) {
		this.enderecoid = enderecoid;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
