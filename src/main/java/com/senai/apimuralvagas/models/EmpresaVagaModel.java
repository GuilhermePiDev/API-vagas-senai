package com.senai.apimuralvagas.models;

import jakarta.persistence.*;


@Entity
@Table(name = "empresa_vaga")
public class EmpresaVagaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int empresaVagaId;
	
	@OneToMany
	@Column(name = "empresa_id")
	private EmpresaModel empresaId;
	@OneToMany
	@Column(name = "vaga_id")
	
	private VagaModel vagaId;
	public int getEmpresaVagaId() {
		return empresaVagaId;
	}
	public void setEmpresaVagaId(int empresaVagaId) {
		this.empresaVagaId = empresaVagaId;
	}
	public EmpresaModel getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(EmpresaModel empresaId) {
		this.empresaId = empresaId;
	}
	public VagaModel getVagaId() {
		return vagaId;
	}
	public void setVagaId(VagaModel vagaId) {
		this.vagaId = vagaId;
	}
	
	

}
