package com.taynahamaral.confectionery.controller.user;

import com.taynahamaral.confectionery.controller.user.dto.RegisterUserRequest;
import com.taynahamaral.confectionery.controller.user.dto.UserResponse;
import com.taynahamaral.confectionery.domain.user.User;
import com.taynahamaral.confectionery.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        User user = userService.registerUser(
                request.name(),
                request.email(),
                request.password(),
                request.gender(),
                String.valueOf(request.birthDate()),
                request.whatsapp()
        );

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getActive(),
                user.getRoles()
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toSet())
        );

        return ResponseEntity
                .created(URI.create("/api/users/" + user.getId()))
                .body(response);
    }
}
