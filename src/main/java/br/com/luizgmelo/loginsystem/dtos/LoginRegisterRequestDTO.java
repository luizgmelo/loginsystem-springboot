package br.com.luizgmelo.loginsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRegisterRequestDTO(
                @NotBlank @Size(min = 5, message = "Username must be at least 5 characters") String username,
                @NotBlank @Size(min = 8, max = 72, message = "Password must between 8 and 72 characters") String password) {
}
