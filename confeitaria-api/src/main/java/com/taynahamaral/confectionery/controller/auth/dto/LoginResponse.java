package com.taynahamaral.confectionery.controller.auth.dto;

import java.util.Set;
import java.util.UUID;

public record LoginResponse(
        UUID id,
        String name,
        String email,
        Set<String> roles,
        String token
) {}
