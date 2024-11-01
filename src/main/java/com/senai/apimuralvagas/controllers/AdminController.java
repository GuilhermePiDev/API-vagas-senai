package com.senai.apimuralvagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.EmpresaService;


import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Admin", description = "Endpoints relacionados ao usuario Admin")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmpresaService empresaService;

    

    @PatchMapping("/autorizar/{empresaId}")
    public EmpresaModel autorizarEmpresa(@PathVariable Integer empresaId) {
        return empresaService.autorizarEmpresa(empresaId);
    }


}
