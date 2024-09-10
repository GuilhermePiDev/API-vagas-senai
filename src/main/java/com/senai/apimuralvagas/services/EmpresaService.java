package com.senai.apimuralvagas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;


@Service
public class EmpresaService {
	@Autowired
	private EmpresaRepo empresaRepo;
	
	public  List<EmpresaModel> returnAllEmpresas(){
		if(empresaRepo != null) {
			return empresaRepo.findAll();
		}
		else {
			return null;
		}
		
	}
	@Transactional
	public EmpresaModel postEmpresa(EmpresaModel empresaModel) {
		return empresaRepo.save(empresaModel);
	}
}
