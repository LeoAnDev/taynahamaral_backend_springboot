package com.taynahamaral.confectionery.controller.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        ErrorCode code,
        Map<String, String> fields
) {}
