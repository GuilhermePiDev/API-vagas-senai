package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "beneficios")
@Data
public class BeneficiosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beneficio_id")
	private int beneficioId;
	private String beneficio;
	

}
