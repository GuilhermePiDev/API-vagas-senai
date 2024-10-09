package com.senai.apimuralvagas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.models.VagaModel;
import com.senai.apimuralvagas.repositorys.VagaRepo;

@Service
public class VagaService {

    @Autowired
    private VagaRepo vagaRepo;

    public List<VagaModel> returnAllVagas(){
        return vagaRepo.findAll();

    }

    public VagaModel postVaga(VagaModel vaga){
        return vagaRepo.save(vaga);
    }
    
}
