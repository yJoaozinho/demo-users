package com.ecommerce.demo_users.web.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserChangeEmailDTO {
    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;
    @NotBlank(message = "Novo Email não pode estar vazio")
    @Email(message = "Novo Email inválido")
    private String newEmail;
    @NotBlank(message = "confirmação Email não pode estar vazio")
    @Email(message = " confirmação Email inválido")
    private String confirmedEmail;

}
