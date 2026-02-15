package com.taynahamaral.confectionery.service.auth;

import com.taynahamaral.confectionery.controller.auth.dto.LoginRequest;
import com.taynahamaral.confectionery.controller.auth.dto.LoginResponse;
import com.taynahamaral.confectionery.domain.user.User;
import com.taynahamaral.confectionery.domain.user.exception.InvalidCredentialsException;
import com.taynahamaral.confectionery.repository.user.UserRepository;
import com.taynahamaral.confectionery.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {

        // Buscar usuário pelo email
        User user = userRepository.findByEmailWithRoles(request.email())
                .orElseThrow(() -> new InvalidCredentialsException());

        // Verificar senha
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        // Gerar token JWT
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet())
        );

        // Retornar dados do usuário + token
        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet()),
                token
        );
    }
}
