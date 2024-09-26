package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;

	@NotBlank()
	private String nome;

	@NotBlank()
	private String senha;

	@NotBlank
	@Size(min= 11, max = 14)
	@NotBlank()
	private String cpf;

	@NotBlank()
	@Email()
	private String email;

}
