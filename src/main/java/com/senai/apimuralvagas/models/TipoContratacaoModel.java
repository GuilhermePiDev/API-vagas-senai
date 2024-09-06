package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tipo_contratacao")
@Data
public class TipoContratacaoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_contratacao_id")
	private int tipoContratacaoId;
	private String tipo;
	
	
}
