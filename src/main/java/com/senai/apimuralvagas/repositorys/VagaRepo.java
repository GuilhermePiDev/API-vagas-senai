package com.senai.apimuralvagas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.apimuralvagas.models.VagaModel;

public interface VagaRepo extends JpaRepository<VagaModel, Integer>{
    
}
