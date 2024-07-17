package com.example.avito.user.controller;

import com.example.avito.user.Role;
import com.example.avito.user.User;
import com.example.avito.user.dto.JwtAuthenticationResponse;
import com.example.avito.user.dto.SignUpRequest;
import com.example.avito.user.service.AuthenticationService;
import com.example.avito.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping()
    public JwtAuthenticationResponse createUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUp(signUpRequest);
    }

   @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<Object> updateRoleUser(@PathVariable Long userId, @RequestBody Map<String,String> request){
        try {
            String roleString = request.get("role");
            if (roleString == null || roleString.isEmpty()) {
                return ResponseEntity.badRequest().body("Role is required");
            }

            Role role;
            try {
                role = Role.valueOf(roleString);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid role");
            }

            User updatedUser = userService.updateRole(userId, role);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
