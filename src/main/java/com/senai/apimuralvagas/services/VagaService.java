package com.senai.apimuralvagas.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.senai.apimuralvagas.exceptions.CustomAccessException;
import com.senai.apimuralvagas.exceptions.EntityNotFoundException;
import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.models.EmpresaVagaModel;
import com.senai.apimuralvagas.models.VagaModel;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;
import com.senai.apimuralvagas.repositorys.EmpresaVagaRepo;
import com.senai.apimuralvagas.repositorys.VagaRepo;

import jakarta.transaction.Transactional;

@Service
public class VagaService {

    @Autowired
    private VagaRepo vagaRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    @Autowired
    private EmpresaVagaRepo empresaVagaRepo;

    public List<VagaModel> returnAllVagas() {
        return vagaRepo.findAll();

    }

    public VagaModel returnOneVaga(Integer id) {
        existVaga(id);
        return vagaRepo.findById(id).orElse(null);
    }

    @Transactional
    public VagaModel postVaga(VagaModel vaga) {
      
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

      
        EmpresaModel usuario = empresaRepo.findByEmail(email);

        vaga = vagaRepo.save(vaga);

        EmpresaVagaModel empresaVaga = new EmpresaVagaModel();
        empresaVaga.setEmpresaId(usuario);
        empresaVaga.setVagaId(vaga); 

        
        empresaVagaRepo.save(empresaVaga);

        return vaga;
    }

    @Transactional
    public VagaModel updateVagaParcial(VagaModel empresaPatch, int id) throws CustomAccessException {
        existVaga(id);

        if (!isOwnerOfVaga(id)) {
            throw new CustomAccessException(

            );
        }

        VagaModel empresaExistente = vagaRepo.findById(id).orElse(null);
        Field[] fields = VagaModel.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("empresaId")||field.getName().equals("cnpj")) {
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

    @Transactional
    public void deleteVaga(int id) throws CustomAccessException {
        existVaga(id);
    
        if (!isOwnerOfVaga(id)) {
            throw new CustomAccessException();
        }
    

        empresaVagaRepo.deleteByVagaId(id);

        vagaRepo.deleteById(id);
    }
    



    private boolean isOwnerOfVaga(int vagaId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        EmpresaVagaModel empresaVaga = empresaVagaRepo.findById(vagaId).orElse(null);
        return empresaVaga != null && empresaVaga.getEmpresaId().getEmail().equals(email);
    }

    private void existVaga(Integer id) {
        if (!vagaRepo.existsById(id)) {
            throw new EntityNotFoundException("Vaga", id);
        }
    }

}
