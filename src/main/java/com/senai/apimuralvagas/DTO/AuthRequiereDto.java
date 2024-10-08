package com.senai.apimuralvagas.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthRequiereDto(@NotBlank String email,@NotBlank String senha) {
    
}
