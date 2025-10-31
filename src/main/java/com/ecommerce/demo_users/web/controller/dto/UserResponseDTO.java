package com.ecommerce.demo_users.web.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String role;

}
