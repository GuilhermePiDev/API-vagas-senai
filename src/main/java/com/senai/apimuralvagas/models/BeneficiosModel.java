package com.senai.apimuralvagas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "beneficios")
public class BeneficiosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beneficioId;
	private String beneficio;
	
	public int getBeneficioId() {
		return beneficioId;
	}
	public void setBeneficioId(int beneficioId) {
		this.beneficioId = beneficioId;
	}
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	

}
