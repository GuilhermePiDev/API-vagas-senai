package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "descricao_empresa")
@Data
public class DescricaoEmpresaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "descricao_id")
	 private int descricaoId;
	 private String ramo;
	 private String site;
	 private int qntdFuncionarios;
	 private String descricao;
	 
}
