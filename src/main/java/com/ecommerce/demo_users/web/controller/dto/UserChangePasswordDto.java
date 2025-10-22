package com.ecommerce.demo_users.web.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserChangePasswordDto {
    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;
    @NotBlank(message = "Nova Senha não pode estar vazia")
    @Size(min = 6, message = "Nova Senha deve ter no mínimo 6 caracteres")
    private String newPassword;
    @NotBlank(message = "Confirma senha não pode estar vazia")
    @Size(min = 6, message = "Confirma senha deve ter no mínimo 6 caracteres")
    private String confirmedPassword;

}
