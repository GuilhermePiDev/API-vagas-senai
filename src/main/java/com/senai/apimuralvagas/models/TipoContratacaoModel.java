package com.senai.apimuralvagas.models;

import jakarta.persistence.*;

@Entity
@Table(name="tipoContratacao")
public class TipoContratacaoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tipoContratacaoId;
	private String tipo;
	
	
	public int getTipoContratacaoId() {
		return tipoContratacaoId;
	}
	public void setTipoContratacaoId(int tipoContratacaoId) {
		this.tipoContratacaoId = tipoContratacaoId;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
