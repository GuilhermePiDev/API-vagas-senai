package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "endereco")
@Data
public class EnderecoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_id")
	private int enderecoid;
	private String cep;
	private String rua;
	private String bairro;
	private String numero;
	private String cidade;
	private String pais;

}
