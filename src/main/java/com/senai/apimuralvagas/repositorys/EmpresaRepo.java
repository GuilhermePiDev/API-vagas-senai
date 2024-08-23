package com.senai.apimuralvagas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.apimuralvagas.models.EmpresaModel;

public interface EmpresaRepo extends JpaRepository<EmpresaModel, Integer> {

}
