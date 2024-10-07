package com.senai.apimuralvagas.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(@NotBlank String email,@NotBlank String senha) {
    
}
