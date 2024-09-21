package com.senai.apimuralvagas.repositorys;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.apimuralvagas.models.AdminModel;

public interface AdminRepo extends JpaRepository<AdminModel, Integer>{
    Optional<AdminModel> findByCpf(String cpf);
}
