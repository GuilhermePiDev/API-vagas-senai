package com.senai.apimuralvagas.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senai.apimuralvagas.models.EmpresaVagaModel;

@Repository
public interface EmpresaVagaRepo extends JpaRepository<EmpresaVagaModel, Integer> {

    @Modifying
    @Query("DELETE FROM EmpresaVagaModel ev WHERE ev.vagaId.vagaId = :vagaId")
    void deleteByVagaId(@Param("vagaId") int vagaId);

    @Query("SELECT ev FROM EmpresaVagaModel ev WHERE ev.empresaId.empresaId = :empresaId")
    Page<EmpresaVagaModel> findByEmpresaId(@Param("empresaId") int empresaId, Pageable pageable);

    @Modifying
    @Query("DELETE FROM EmpresaVagaModel ev WHERE ev.empresaId.empresaId = :empresaId")
    void deleteByEmpresaId(@Param("empresaId") int empresaId);
    

}
