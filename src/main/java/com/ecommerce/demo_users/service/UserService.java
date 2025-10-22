package com.ecommerce.demo_users.service;

import com.ecommerce.demo_users.entity.User;
import com.ecommerce.demo_users.exception.EmailUniqueViolationExcepiton;
import com.ecommerce.demo_users.exception.EntityNotFoundException;
import com.ecommerce.demo_users.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user){
        try {
            return userRepository.save(user);
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new EmailUniqueViolationExcepiton(String.format("Email '%s' já cadastrado ", user.getEmail()));
        }
    }
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User getById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id='%s não encontrado. '", id))
        );
    }
    @Transactional(readOnly = true)
    public User getbyEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }
    @Transactional
    public User updateEmail(Long id,String password, String newEmail, String confirmedEmail){
        User user = getById(id);
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("Sua senha não confere. ");
        }
        if(!newEmail.equals(confirmedEmail)){
            throw new RuntimeException("Novo email não confere com confirmação de email. ");
        }
        user.setEmail(newEmail);
        return user;
    }
    @Transactional
    public User updatePassword(Long id, String password, String newPassword, String confirmedPassword){
        if(!newPassword.equals(confirmedPassword)){
            throw new RuntimeException("Nova senha não confere com confirmação de senha. ");
        }
        User user = getById(id);
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("Sua senha não confere. ");
        }
        user.setPassword(newPassword);
        return user;
    }
    @Transactional
    public User updateUsername(Long id, String username, String password) {
        User user = getById(id);
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Sua Senha não confere. ");
        }
        if (user.getUsername().equals(username)){
            throw new RuntimeException("Novo username não pode ser o username atual. ");
        }
        user.setUsername(username);
        return user;
    }
    @Transactional
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
