package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;
	private String nome;
	private String senha;
	private String cpf;
	private String email;

}
