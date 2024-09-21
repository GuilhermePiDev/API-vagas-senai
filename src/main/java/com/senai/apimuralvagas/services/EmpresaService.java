package com.senai.apimuralvagas.services;

import java.util.List;
import java.lang.reflect.Field;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;
import com.senai.apimuralvagas.exceptions.*;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepo empresaRepo;

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

    public void deleteEmpresa(int id) {
        existEmpresa(id);
        empresaRepo.deleteById(id);
    }

    @Transactional
    public EmpresaModel postEmpresa(EmpresaModel empresaModel) {
        Optional<EmpresaModel> existingEmpresa = empresaRepo.findByCnpj(empresaModel.getCnpj());
        if (existingEmpresa.isPresent()) {
            throw new EntityAlreadyExist("Empresa", "Cnpj", empresaModel.getCnpj());
        } else {
            return empresaRepo.save(empresaModel);
        }

    }

    @Transactional
    public EmpresaModel updateEmpresaParcial(EmpresaModel empresaPatch, int id) throws CustomAccessException {

        existEmpresa(id);
        EmpresaModel empresaExistente = empresaRepo.findById(id).orElse(null);

        Field[] fields = EmpresaModel.class.getDeclaredFields();

        for (Field field : fields) {
            // validar para login depois
            field.setAccessible(true);
            try {
            
                if (field.getName().equals("empresaId")) {
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
}
