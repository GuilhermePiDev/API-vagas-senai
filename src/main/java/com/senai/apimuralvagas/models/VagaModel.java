package com.senai.apimuralvagas.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "vaga")
public class VagaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vagaId;
	
    @OneToOne
    @MapsId("tipoContratacaoId")
    @JoinColumn(name = "tipoContratacaoId")
	private TipoContratacaoModel tipoContratacao;
    
    @ManyToOne
    @MapsId("formaCandidaturaId")
    @JoinColumn(name = "formaCandidaturaId")
    private FormaCandidaturaModel formaCandidatura;
    
    private String descricao;
    private String requisitos;
    private int cargaSemanal;
    
    @ManyToOne
    @MapsId("beneficioId")
    @JoinColumn(name = "beneficioId")
    private BeneficiosModel beneficios;
    

    @Column(updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now() ;
    
    private LocalDateTime dataHoraDefinidaPeloUsuario;

	public int getVagaId() {
		return vagaId;
	}

	public void setVagaId(int vagaId) {
		this.vagaId = vagaId;
	}

	public TipoContratacaoModel getTipoContratacao() {
		return tipoContratacao;
	}

	public void setTipoContratacao(TipoContratacaoModel tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}

	public FormaCandidaturaModel getFormaCandidatura() {
		return formaCandidatura;
	}

	public void setFormaCandidatura(FormaCandidaturaModel formaCandidatura) {
		this.formaCandidatura = formaCandidatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public int getCargaSemanal() {
		return cargaSemanal;
	}

	public void setCargaSemanal(int cargaSemanal) {
		this.cargaSemanal = cargaSemanal;
	}

	public BeneficiosModel getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(BeneficiosModel beneficios) {
		this.beneficios = beneficios;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public LocalDateTime getDataHoraDefinidaPeloUsuario() {
		return dataHoraDefinidaPeloUsuario;
	}

	public void setDataHoraDefinidaPeloUsuario(LocalDateTime dataHoraDefinidaPeloUsuario) {
		this.dataHoraDefinidaPeloUsuario = dataHoraDefinidaPeloUsuario;
	}
    
    

}
