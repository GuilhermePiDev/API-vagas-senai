package com.senai.apimuralvagas.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senai.apimuralvagas.models.VagaModel;

public interface VagaRepo extends JpaRepository<VagaModel, Integer>, JpaSpecificationExecutor<VagaModel> {

    VagaModel findByNomeVaga(String nomeVaga);

    @Query("SELECT ev.empresaId.empresaId FROM EmpresaVagaModel ev WHERE ev.vagaId.vagaId = :vagaId")
    Optional<Integer> findCriadorIdByVagaId(@Param("vagaId") Integer vagaId);
    

}
