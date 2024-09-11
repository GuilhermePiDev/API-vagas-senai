package com.senai.apimuralvagas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.EmpresaService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public List<EmpresaModel> returnAllEmpresasC() {
		return empresaService.returnAllEmpresas();
	}

/* 	@PostMapping
	public ResponseEntity<EmpresaModel> criarEmpresa(@Valid @RequestBody EmpresaModel empresa) {
		EmpresaModel novaEmpresa = empresaService.postEmpresa(empresa);
		return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
	} */

	@PostMapping
	public ResponseEntity<EmpresaModel> criarEmpresa(@RequestBody EmpresaModel empresa) {
		System.out.println("Dados recebidos: " + empresa);
		EmpresaModel empresaSalva = empresaService.postEmpresa(empresa);
		return new ResponseEntity<>(empresaSalva, HttpStatus.CREATED);
	}
	

}
