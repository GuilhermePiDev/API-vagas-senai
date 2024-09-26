package com.senai.apimuralvagas.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "vaga")
@Data
public class VagaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaga_id")
	private int vagaId;

    @NotBlank()
	private String nomeVaga;

    @NotBlank()
    @OneToOne
    @JoinColumn(name = "tipo_contratacao_id")
    @Valid
	private TipoContratacaoModel tipoContratacao;

    @NotBlank()
    @ManyToOne
    @JoinColumn(name = "forma_candidatura_id")
    @Valid
    private FormaCandidaturaModel formaCandidatura;

    @NotBlank()
    private String descricao;
    
    @NotBlank()
    private String requisitos;

    @Min(value = 1)
    private int cargaSemanal;

    @NotBlank()
    @ManyToOne
    @JoinColumn(name = "beneficio_id")
    @Valid
    private BeneficiosModel beneficios; 

    @Min(value = 0)
	private double salario;

    @Min(value = 1)
	private int qtdVagasDisponiveis;

    @Column(updatable = false)
    private LocalDateTime dataPublicacao = LocalDateTime.now() ;

    @Future()
    private LocalDateTime dataExpiracao;

    @OneToMany(mappedBy = "vagaId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
    private Set<EmpresaVagaModel> empresaVagas;

}
