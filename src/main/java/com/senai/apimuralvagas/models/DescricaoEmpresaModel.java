package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "descricao_empresa")
@Data
public class DescricaoEmpresaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "descricao_id")
	 private int descricaoId;

	@NotBlank
	 private String ramo;

	 private String site;

	@Min(value = 1)
	private int qntdFuncionarios;

	@NotBlank
	 private String descricao;
	 
}
