package com.senai.apimuralvagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<VagaModel>> returnAllVagas(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VagaModel> vagas = vagaService.returnAllVagas(pageable);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaModel> returnOneVaga(@PathVariable int id) {
        VagaModel vagaModel = vagaService.returnOneVaga(id);
        return new ResponseEntity<>(vagaModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VagaModel> postVaga(@Valid @RequestBody VagaModel vaga) {
        VagaModel vagaModel = vagaService.postVaga(vaga);
        return new ResponseEntity<>(vagaModel, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VagaModel> updateVaga(@PathVariable int id, @RequestBody VagaModel vaga)
            throws CustomAccessException {
        VagaModel vagaModel = vagaService.updateVagaParcial(vaga, id);
        return new ResponseEntity<>(vagaModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable int id) throws CustomAccessException {
        vagaService.deleteVaga(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
