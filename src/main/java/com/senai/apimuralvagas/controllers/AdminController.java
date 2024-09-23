package com.senai.apimuralvagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.senai.apimuralvagas.models.AdminModel;
import com.senai.apimuralvagas.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminModel> criarAdmin(@RequestBody AdminModel admin) {
        adminService.postAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
}
