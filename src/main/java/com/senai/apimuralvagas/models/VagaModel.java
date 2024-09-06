package com.senai.apimuralvagas.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vaga")
@Data
public class VagaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaga_id")
	private int vagaId;
	private String nomeVaga;
    @OneToOne
    @JoinColumn(name = "tipo_contratacao_id")
	private TipoContratacaoModel tipoContratacao;
    @ManyToOne
    @JoinColumn(name = "forma_candidatura_id")
    private FormaCandidaturaModel formaCandidatura;
    private String descricao;
    private String requisitos;
    private int cargaSemanal;
    @ManyToOne
    @JoinColumn(name = "beneficio_id")
    private BeneficiosModel beneficios; 
	private double salario;
	private int qtdVagasDisponiveis;
    @Column(updatable = false)
    private LocalDateTime dataPublicacao = LocalDateTime.now() ;
    private LocalDateTime dataExpiracao;
    @OneToMany(mappedBy = "vagaId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
    private Set<EmpresaVagaModel> empresaVagas;

}
