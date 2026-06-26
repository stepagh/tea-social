package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.UserRepository;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.exceptions.EmailAlreadyExistsException;
import com.dadaev.tea_social.exceptions.UsernameAlreadyExistsException;
import com.dadaev.tea_social.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ProfileService profileService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException("This Username is already exists");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("This email is already taken");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.saveAndFlush(user);
        profileService.initializeProfile(user);

        String token = jwtService.generateToken(user.getId(), user.getUsername());
        UserDto userDTO = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        return new AuthResponse(token, userDTO);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Wrong username or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Wrong username or password");
        }

        String token = jwtService.generateToken(user.getId(), user.getUsername());
        UserDto userDTO = new UserDto(user.getId(), user.getUsername(), user.getEmail());

        return new AuthResponse(token, userDTO);
    }
}
