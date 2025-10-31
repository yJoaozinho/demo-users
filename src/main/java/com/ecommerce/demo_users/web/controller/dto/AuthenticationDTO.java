package com.ecommerce.demo_users.web.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationDTO {

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;

}
