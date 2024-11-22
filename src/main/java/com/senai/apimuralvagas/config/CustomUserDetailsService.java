package com.senai.apimuralvagas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.exceptions.UserNotFoundException;
import com.senai.apimuralvagas.models.AdminModel;
import com.senai.apimuralvagas.models.EmpresaModel;
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
    AdminModel admin = adminRepo.findByEmail(email);
    if (admin != null) {
        return new CustomUserDetails(
                admin.getAdminId(),
                admin.getEmail(),
                admin.getSenha(),
                admin.getAuthorities()
        );
    }

    EmpresaModel empresa = empresaRepo.findByEmail(email);
    if (empresa != null) {
        return new CustomUserDetails(
                empresa.getEmpresaId(),
                empresa.getEmail(),
                empresa.getSenha(),
                empresa.getAuthorities()
        );
    }

    throw new UserNotFoundException(email);
}


}
