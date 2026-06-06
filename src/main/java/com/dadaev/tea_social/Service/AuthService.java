package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.UserRepository;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new AuthException("Это имя пользователя уже занято");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new AuthException("Этот email уже зарегистрирован");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);

        String token = jwtService.generateToken(user.getId(), user.getUsername());
        UserDTO userDTO = new UserDTO(user.getId().toString(), user.getUsername(), user.getEmail());

        return new AuthResponse(token, userDTO);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new AuthException("Неверное имя пользователя или пароль"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthException("Неверное имя пользователя или пароль");
        }

        String token = jwtService.generateToken(user.getId(), user.getUsername());
        UserDTO userDTO = new UserDTO(user.getId().toString(), user.getUsername(), user.getEmail());

        return new AuthResponse(token, userDTO);
    }
}
