package com.senai.apimuralvagas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "empresa_vaga")
@Data
public class EmpresaVagaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "empresa_vaga_id")
	private int empresaVagaId;

	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)
	@JsonBackReference
	private EmpresaModel empresaId;
	
	@ManyToOne
	@JoinColumn(name = "vaga_id" , nullable = false)
	@JsonBackReference
	private VagaModel vagaId;
	

}
