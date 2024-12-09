package com.senai.apimuralvagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.exceptions.CustomAccessException;
import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.models.VagaModel;
import com.senai.apimuralvagas.services.EmpresaService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Empresa", description = "Endpoints relacionados ao usuario Empresa")
@RestController
@RequestMapping("empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
       
    @GetMapping
    public ResponseEntity<Page<EmpresaModel>> returnAllEmpresasC(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(empresaService.returnAllEmpresas(pageable));
    }

    @GetMapping("/true")
    public ResponseEntity<Page<EmpresaModel>> returnTrue(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(empresaService.returnTrue(pageable));
    }

    @GetMapping("/false")
    public ResponseEntity<Page<EmpresaModel>> returnFalse(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(empresaService.returnFalse(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel> returnOneEmpresa(@PathVariable int id) {
        EmpresaModel empresa = empresaService.returnOneEmpresa(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable int id) throws CustomAccessException {
        empresaService.deleteEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaModel> patchEmpresa(@PathVariable int id, @RequestBody EmpresaModel empresaPatch)
            throws CustomAccessException {
        EmpresaModel empresa = empresaService.updateEmpresaParcial(empresaPatch, id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @GetMapping("/{empresaId}/vagas")
    public ResponseEntity<Page<VagaModel>> getVagasByEmpresaId(
            @PathVariable int empresaId,
            Pageable pageable) {

        Page<VagaModel> vagas = empresaService.findVagasByEmpresaId(empresaId, pageable);
        if (vagas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(vagas, HttpStatus.OK);
    }

}
