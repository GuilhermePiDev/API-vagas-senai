package com.senai.apimuralvagas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.EmpresaService;

@RestController
@RequestMapping("empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public List<EmpresaModel> returnAll(){
		return empresaService.returnEmpresa();
	}

}
