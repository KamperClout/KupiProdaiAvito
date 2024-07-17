package com.example.avito.user.service;

import com.example.avito.user.Role;
import com.example.avito.user.User;
import com.example.avito.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;

    public User create(User user) {
        if (repository.existsByName(user.getName())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return repository.save(user);
    }

    public User getByUsername(String username) {
        return repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public List<User> findAll(){
       return repository.findAll().stream().collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(()->
                new RuntimeException("Пользователь с id " + id + " не найден"));
    }

    public void deleteUser(Long userId){
        try {
            repository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Пользователь с ID=" + userId + " не найден!");
        }
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        repository.save(user);
    }

    public User updateRole(Long userId, Role role){
        User user = repository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        user.setRole(role);
        return repository.save(user);
    }
}
