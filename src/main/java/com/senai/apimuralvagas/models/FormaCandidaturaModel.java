package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name="forma_candidatura")
@Data
public class FormaCandidaturaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "forma_candidatura_id")
	private int formaCandidaturaId;

	@NotBlank
	private String formaCandidatura;

}
