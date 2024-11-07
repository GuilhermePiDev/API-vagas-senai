package com.senai.apimuralvagas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.apimuralvagas.models.EmpresaVagaModel;

@Repository
public interface EmpresaVagaRepo extends JpaRepository<EmpresaVagaModel, Integer> {
    
}
