package com.senai.apimuralvagas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "descricao_empresa")
public class DescricaoEmpresaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "descricao_id")
	 private int descricaoId;
	 private String ramo;
	 private String site;
	 private int qntdFuncionarios;
	 private String descricao;
	 
	 
	public int getDescricaoId() {
		return descricaoId;
	}
	public void setDescricaoId(int descricaoId) {
		this.descricaoId = descricaoId;
	}
	public String getRamo() {
		return ramo;
	}
	public void setRamo(String ramo) {
		this.ramo = ramo;
	}
	
	public int getQntdFuncionarios() {
		return qntdFuncionarios;
	}
	public void setQntdFuncionarios(int qntdFuncionarios) {
		this.qntdFuncionarios = qntdFuncionarios;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	 
	 
}
