package com.ecommerce.demo_users.web.controller;

import com.ecommerce.demo_users.config.security.TokenService;
import com.ecommerce.demo_users.entity.User;
import com.ecommerce.demo_users.repository.UserRepository;
import com.ecommerce.demo_users.web.controller.dto.AuthenticationDTO;
import com.ecommerce.demo_users.web.controller.dto.UserCreateDTO;
import com.ecommerce.demo_users.web.controller.dto.mapper.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var emailPassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword());
        var auth = this.authenticationManager.authenticate(emailPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserCreateDTO userCreateDTO){
        if (this.userRepository.findByEmail(userCreateDTO.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userCreateDTO.getPassword());
        User user = new User(userCreateDTO.getUsername(), userCreateDTO.getEmail(), encryptedPassword);

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }


}
