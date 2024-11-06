package com.senai.apimuralvagas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.VagaModel;
import com.senai.apimuralvagas.services.VagaService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    private VagaService vagaService;

    @GetMapping
    public List<VagaModel> returnAllVagas(){
        return vagaService.returnAllVagas();
    }

    @PostMapping
    public VagaModel postVaga(@Valid @RequestBody VagaModel vaga){
        return vagaService.postVaga(vaga);
    }
}
