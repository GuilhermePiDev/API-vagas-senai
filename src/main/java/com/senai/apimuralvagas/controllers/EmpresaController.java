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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.EmpresaService;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<EmpresaModel> returnAllEmpresasC() {
        return empresaService.returnAllEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel> returnOneEmpresa(@PathVariable int id) {
        EmpresaModel empresa = empresaService.returnOneEmpresa(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpresaModel> criarEmpresa(@RequestBody EmpresaModel empresa) {
        empresaService.postEmpresa(empresa);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable int id) {
        empresaService.deleteEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaModel> patchEmpresa(@PathVariable int id, @RequestBody EmpresaModel empresaPatch) {
        EmpresaModel empresa = empresaService.updateEmpresaParcial(empresaPatch, id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
}
