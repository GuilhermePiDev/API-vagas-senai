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
        Optional<AdminModel> adminCpf = adminRepo.findByCpf(admin.getCpf());
        Boolean validCpf = CpfValidator(admin.getCpf());

        if (validCpf) {
            if (adminCpf.isPresent()) {
                throw new EntityAlreadyExist("Usuario","Cpf",admin.getCpf());
            } 
            else {
                return adminRepo.save(admin);
            }
        }
        else{
            throw new InvalidDataException("Cpf", admin.getCpf());
        }
        

    }

    public static boolean CpfValidator(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); 
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        // Cálculo do 1º 
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        
        int firstCheck = 11 - (sum % 11);
        
        if (firstCheck >= 10) {
            firstCheck = 0;
        }
        
        if (firstCheck != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Cálculo do 2º 
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        
        int secondCheck = 11 - (sum % 11);
        
        if (secondCheck >= 10) {
            secondCheck = 0;
        }
        
        return secondCheck == (cpf.charAt(10) - '0');
    }
    

}
