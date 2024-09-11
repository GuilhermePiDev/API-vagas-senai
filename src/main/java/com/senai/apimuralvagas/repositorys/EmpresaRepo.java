package com.senai.apimuralvagas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.apimuralvagas.models.EmpresaModel;

@Repository
public interface EmpresaRepo extends JpaRepository<EmpresaModel, Integer> {

}
