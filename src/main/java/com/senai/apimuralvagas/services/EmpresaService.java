package com.senai.apimuralvagas.services;

import java.util.Collections;
import java.util.List;
import java.lang.reflect.Field;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;

import com.senai.apimuralvagas.exceptions.*;

@Service("empresaService")
public class EmpresaService {
    @Autowired
    private EmpresaRepo empresaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<EmpresaModel> returnAllEmpresas() {
        if (empresaRepo != null) {
            return empresaRepo.findAll();
        } else {
            return null;
        }

    }

    public EmpresaModel returnOneEmpresa(int id) {
        existEmpresa(id);
        return empresaRepo.findById(id).orElse(null);

    }

    public List<EmpresaModel> returnTrue() {
        if (empresaRepo != null) {
            return empresaRepo.findAll().stream()
                    .filter(empresa -> empresa.getAutorizacao() == true) 
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public List<EmpresaModel> returnFalse() {
        if (empresaRepo != null) {
            return empresaRepo.findAll().stream()
                    .filter(empresa -> empresa.getAutorizacao() == false) 
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public void deleteEmpresa(int id) {
        existEmpresa(id);
        empresaRepo.deleteById(id);
    }

    @Transactional
    public EmpresaModel postEmpresa(EmpresaModel empresaModel) {
        empresaModel.setSenha(passwordEncoder.encode(empresaModel.getSenha()));

        Optional<EmpresaModel> existingEmpresa = empresaRepo.findByCnpj(empresaModel.getCnpj());
        Boolean validCnpj = isValidCNPJ(empresaModel.getCnpj());
        if (validCnpj) {
            if (existingEmpresa.isPresent()) {
                throw new EntityAlreadyExist("Empresa", "Cnpj", empresaModel.getCnpj());
            } else {
                return empresaRepo.save(empresaModel);
            }
        } else {
            throw new InvalidDataException("Cnpj", empresaModel.getCnpj());
        }

    }

    @Transactional
    public EmpresaModel updateEmpresaParcial(EmpresaModel empresaPatch, int id) throws CustomAccessException {

        existEmpresa(id);
        EmpresaModel empresaExistente = empresaRepo.findById(id).orElse(null);

        Field[] fields = EmpresaModel.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {

                if (field.getName().equals("empresaId") || field.getName().equals("autorizacao")) {
                    continue;
                }
                Object value = field.get(empresaPatch);
                if (value != null) {
                    field.set(empresaExistente, value);
                }

            } catch (IllegalAccessException e) {
                throw new CustomAccessException();
            }

        }

        return empresaRepo.save(empresaExistente);
    }

    private void existEmpresa(Integer id) {
        if (!empresaRepo.existsById(id)) {
            throw new EntityNotFoundException("Empresa", id);
        }
    }

    public static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", ""); // Remove caracteres não numéricos
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Cálculo do 1º dígito verificador
        int[] weight1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weight1[i];
        }

        int firstCheck = 11 - (sum % 11);

        if (firstCheck >= 10) {
            firstCheck = 0;
        }

        if (firstCheck != (cnpj.charAt(12) - '0')) {
            return false;
        }

        // Cálculo do 2º dígito verificador
        int[] weight2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weight2[i];
        }

        int secondCheck = 11 - (sum % 11);

        if (secondCheck >= 10) {
            secondCheck = 0;
        }

        return secondCheck == (cnpj.charAt(13) - '0');
    }

    public EmpresaModel autorizarEmpresa(Integer id) {

        existEmpresa(id);
        EmpresaModel empresa = empresaRepo.findById(id).orElse(null);
        empresa.setAutorizacao(true);
        return empresaRepo.save(empresa);
    }

}
