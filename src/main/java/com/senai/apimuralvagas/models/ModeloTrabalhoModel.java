package com.senai.apimuralvagas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="modelo_trabalho")
@Data
public class ModeloTrabalhoModel {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modelo_trabalho_id")
	private int modeloTrabalhoId;

	@NotBlank
	private String modeloTrabalho;
}
