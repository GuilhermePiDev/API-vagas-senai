package com.senai.apimuralvagas.repositorys;

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

}
