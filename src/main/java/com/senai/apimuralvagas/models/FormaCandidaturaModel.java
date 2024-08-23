package com.senai.apimuralvagas.models;

import jakarta.persistence.*;


@Entity
@Table(name="forma_candidatura")
public class FormaCandidaturaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "forma_candidatura_id")
	private int formaCandidaturaId;
	private String formaCandidatura;
	
	public int getFormaCandidaturaId() {
		return formaCandidaturaId;
	}
	public void setFormaCandidaturaId(int formaCandidaturaId) {
		this.formaCandidaturaId = formaCandidaturaId;
	}
	public String getFormaCandidatura() {
		return formaCandidatura;
	}
	public void setFormaCandidatura(String formaCandidatura) {
		this.formaCandidatura = formaCandidatura;
	}
	

}
