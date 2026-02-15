package com.taynahamaral.confectionery.controller.auth;

import com.taynahamaral.confectionery.controller.auth.dto.LoginRequest;
import com.taynahamaral.confectionery.controller.auth.dto.LoginResponse;
import com.taynahamaral.confectionery.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
