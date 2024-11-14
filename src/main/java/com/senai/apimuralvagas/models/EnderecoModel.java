package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name = "endereco")
@Data
public class EnderecoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_id")
	private int enderecoid;

	@NotBlank
	@Size(min = 8, max = 9)
	private String cep;

	@NotBlank
	private String rua;
	
	@NotBlank
	private String bairro;

	@NotBlank
	@Min(0)
	private String numero;

	@NotBlank
	private String cidade;

	@NotBlank
	private String pais;

	@NotBlank
	private String estado;

}
