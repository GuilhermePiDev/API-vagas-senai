package com.senai.apimuralvagas.DTO;

import java.util.List;

public record AuthResponseDTO(String token, List<String> roles) {
    
}
