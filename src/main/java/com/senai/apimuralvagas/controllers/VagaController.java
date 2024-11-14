package com.senai.apimuralvagas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.exceptions.CustomAccessException;
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
    public ResponseEntity<VagaModel> postVaga(@Valid @RequestBody VagaModel vaga){
        VagaModel vagaModel = vagaService.postVaga(vaga);
        return new ResponseEntity<>(vagaModel, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VagaModel> updateVaga(@PathVariable int id, @RequestBody VagaModel vaga) throws CustomAccessException{
        VagaModel vagaModel = vagaService.updateVagaParcial(vaga, id);
        return new ResponseEntity<>(vagaModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable int id) throws CustomAccessException{
        vagaService.deleteVaga(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    } 
}
