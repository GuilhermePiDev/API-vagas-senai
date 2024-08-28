package com.senai.apimuralvagas.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "vaga")
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

	public int getVagaId() {
		return vagaId;
	}

	public void setVagaId(int vagaId) {
		this.vagaId = vagaId;
	}

	public String getNomeVaga() {
		return nomeVaga;
	}

	public void setNomeVaga(String nomeVaga) {
		this.nomeVaga = nomeVaga;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getQtdVagasDisponiveis() {
		return qtdVagasDisponiveis;
	}

	public void setQtdVagasDisponiveis(int qtdVagasDisponiveis) {
		this.qtdVagasDisponiveis = qtdVagasDisponiveis;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataPublicacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataPublicacao = dataHoraCriacao;
	}

	public LocalDateTime getDataHoraDefinidaPeloUsuario() {
		return dataExpiracao;
	}

	public void setDataHoraDefinidaPeloUsuario(LocalDateTime dataHoraDefinidaPeloUsuario) {
		this.dataExpiracao = dataHoraDefinidaPeloUsuario;
	}

	public Set<EmpresaVagaModel> getEmpresaVagas() {
		return empresaVagas;
	}

	public void setEmpresaVagas(Set<EmpresaVagaModel> empresaVagas) {
		this.empresaVagas = empresaVagas;
	}

	
	
    
    

}
