package com.senai.apimuralvagas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaRepo empresaRepo;
	
	public  List<EmpresaModel> returnEmpresa(){
		if(empresaRepo != null) {
			return empresaRepo.findAll();
		}
		else {
			return null;
		}
	}
}
