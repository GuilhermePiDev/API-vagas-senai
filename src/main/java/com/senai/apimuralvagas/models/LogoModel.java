package com.senai.apimuralvagas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "logo")
@Data
public class LogoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logo_id")
	private int logoId;

	@NotBlank
	private String linkLogo;
	
}
