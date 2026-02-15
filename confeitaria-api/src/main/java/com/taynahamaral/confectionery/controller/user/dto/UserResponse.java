package com.taynahamaral.confectionery.controller.user.dto;

import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Boolean active,
        Set<String>roles
) {
}
