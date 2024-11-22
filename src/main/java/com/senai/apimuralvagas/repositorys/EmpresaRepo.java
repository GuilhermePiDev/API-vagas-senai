package com.senai.apimuralvagas.repositorys;

import  java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.apimuralvagas.models.EmpresaModel; 


@Repository
public interface EmpresaRepo extends JpaRepository<EmpresaModel, Integer> {
    Optional<EmpresaModel> findByCnpj(String cnpj);
    
    EmpresaModel findByEmail(String email);

    EmpresaModel findByNomeEmpresa(String nomeEmpresa);

    Page<EmpresaModel> findByAutorizacaoTrue(Pageable pageable); 
    Page<EmpresaModel> findByAutorizacaoFalse(Pageable pageable); 
}
