package com.senai.apimuralvagas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.models.AdminModel;
import com.senai.apimuralvagas.repositorys.AdminRepo;
import java.util.Optional;

import com.senai.apimuralvagas.exceptions.*;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public AdminModel postAdmin(AdminModel admin) {
        Optional<AdminModel> adminExist = adminRepo.findByCpf(admin.getCpf());
        if (adminExist.isPresent()) {
            throw new EntityAlreadyExist("Usuario","Cpf",admin.getCpf());
        } 
        else {
            return adminRepo.save(admin);
        }

    }

}
