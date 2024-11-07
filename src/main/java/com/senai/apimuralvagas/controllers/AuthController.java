package com.senai.apimuralvagas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.DTO.AuthRequiereDto;
import com.senai.apimuralvagas.DTO.AuthResponseDTO;
import com.senai.apimuralvagas.models.AdminModel;
import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.AdminService;
import com.senai.apimuralvagas.services.EmpresaService;
import com.senai.apimuralvagas.services.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    @Autowired
    @Qualifier("empresaService")
    private EmpresaService empresaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("cadastro/admin")
    @Operation(summary = "Cadastrar usuario admin")
    public ResponseEntity<AdminModel> criarAdmin(@Valid @RequestBody AdminModel admin) {
        adminService.postAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("cadastro/empresa")
    @Operation(summary = "Cadastrar usuario admin")
    public ResponseEntity<EmpresaModel> criarEmpresa(@Valid @RequestBody EmpresaModel empresa) {
        empresaService.postEmpresa(empresa);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequiereDto auth) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.email(),auth.senha());

        var loginUser = authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = (UserDetails) loginUser.getPrincipal();
        var token = tokenService.generateToken(userDetails);
       
            
        List<String> roles = userDetails.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList());
    
        return new ResponseEntity<>(new AuthResponseDTO(token, roles), HttpStatus.OK);
    }
}
