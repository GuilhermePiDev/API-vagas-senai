package com.senai.apimuralvagas.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.exceptions.CustomAccessException;
import com.senai.apimuralvagas.exceptions.EntityNotFoundException;
import com.senai.apimuralvagas.models.VagaModel;
import com.senai.apimuralvagas.repositorys.VagaRepo;

import jakarta.transaction.Transactional;

@Service
public class VagaService {

    @Autowired
    private VagaRepo vagaRepo;

    public List<VagaModel> returnAllVagas(){
        return vagaRepo.findAll();

    }

    public VagaModel returnOneVaga(Integer id){
        existVaga(id);
        return vagaRepo.findById(id).orElse(null);
    }


    @Transactional
    public VagaModel postVaga(VagaModel vaga){
        return vagaRepo.save(vaga);
    }

    @Transactional
    public VagaModel updateEmpresaParcial(VagaModel empresaPatch, int id) throws CustomAccessException {

        existVaga(id);
        VagaModel empresaExistente = vagaRepo.findById(id).orElse(null);

        Field[] fields = VagaModel.class.getDeclaredFields();

        for (Field field : fields) {
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

        return vagaRepo.save(empresaExistente);
    }


    
    public void deleteVaga(int id) {
        existVaga(id);
        vagaRepo.deleteById(id);
    }

    private void existVaga(Integer id){
        if (!vagaRepo.existsById(id)) {
            throw new EntityNotFoundException("Vaga", id);
        }
    }



    
}
