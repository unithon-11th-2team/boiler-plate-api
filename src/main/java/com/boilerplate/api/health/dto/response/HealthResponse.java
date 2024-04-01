package com.boilerplate.api.health.dto.response;

import java.time.LocalDateTime;

public record HealthResponse(
        String message,
        String environment,
        LocalDateTime dateTime
) {
}
