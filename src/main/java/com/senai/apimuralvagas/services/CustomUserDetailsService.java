package com.senai.apimuralvagas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.repositorys.AdminRepo;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = adminRepo.findByEmail(email);
        if (userDetails != null) {
            return userDetails;
        }
        
        userDetails = empresaRepo.findByEmail(email);
        if (userDetails != null) {
            return userDetails;
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
