package com.senai.apimuralvagas.services;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Map<String, Object>> returnAllVagas(Pageable pageable) {
        return vagaRepo.findAll(pageable).map(vaga -> {
            Optional<Integer> criadorId = vagaRepo.findCriadorIdByVagaId(vaga.getVagaId());

            if (criadorId.isEmpty()) {
                System.out.println("Criador não encontrado para a vaga ID: " + vaga.getVagaId());
            }

            return Map.of(
                    "vaga", vaga,
                    "criadorId", criadorId.orElse(null));
        });
    }

    public Map<String, Object> returnOneVaga(Integer id) {
        existVaga(id);

        VagaModel vaga = vagaRepo.findById(id).orElse(null);
        Optional<Integer> criadorId = vagaRepo.findCriadorIdByVagaId(vaga.getVagaId());
        return Map.of(
                "vaga", vaga,
                "criadorId", criadorId.orElse(null));
    }

    public Page<Map<String, Object>> filterVagas(String nomeVaga, String modeloTrabalho, String formaCandidatura,
            Double salarioMin, Double salarioMax, LocalDateTime dataPublicacao, Pageable pageable) {
        Specification<VagaModel> spec = Specification.where(null);

      

        if (nomeVaga != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("nomeVaga"), "%" + nomeVaga + "%"));
        }
        if (modeloTrabalho != null) {
            spec = spec
                    .and((root, query, cb) -> cb.equal(root.join("modeloTrabalho").get("modeloTrabalho"), modeloTrabalho));
        }
        if (formaCandidatura != null) {
            spec = spec.and(
                    (root, query, cb) -> cb.equal(root.join("formaCandidatura").get("formaCandidatura"), formaCandidatura));
        }
        if (salarioMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("salario"), salarioMin));
        }
        if (salarioMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("salario"), salarioMax));
        }
        if (dataPublicacao != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dataPublicacao"), dataPublicacao));
        }
        

        return vagaRepo.findAll(spec, pageable).map(vaga -> {
            Optional<Integer> criadorId = vagaRepo.findCriadorIdByVagaId(vaga.getVagaId());
            return Map.of(
                    "vaga", vaga,
                    "criadorId", criadorId.orElse(null));
        });
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

        if (!isOwnerOfVaga(id) && !isAdmin()) {
            throw new CustomAccessException();
        }

        VagaModel empresaExistente = vagaRepo.findById(id).orElse(null);
        Field[] fields = VagaModel.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("empresaId") || field.getName().equals("cnpj")) {
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

    public Optional<Integer> getCriadorIdByVagaId(int vagaId) {
        return vagaRepo.findCriadorIdByVagaId(vagaId);
    }

    @Transactional
    public void deleteVaga(int id) throws CustomAccessException {
        existVaga(id);

        if (!isOwnerOfVaga(id) && !isAdmin()) {
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

    private boolean isAdmin() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    private void existVaga(Integer id) {
        if (!vagaRepo.existsById(id)) {
            throw new EntityNotFoundException("Vaga", id);
        }
    }

}
