package com.senai.apimuralvagas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "logo")
public class LogoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logoId;
	private String linkLogo;
	
	public int getLogoId() {
		return logoId;
	}
	public void setLogoId(int logoId) {
		this.logoId = logoId;
	}
	public String getLinkLogo() {
		return linkLogo;
	}
	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}
	
	
}
