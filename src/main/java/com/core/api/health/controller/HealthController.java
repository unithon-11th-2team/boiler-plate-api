package com.core.api.health.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.health.dto.response.HealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

@Tag(name = "Health Check")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HealthController {
    private final Environment environment;
    
    @Operation(summary = "health check")
    @GetMapping("/health")
    public ResponseEntity<ResponseDto<HealthResponse>> health() {
        var response = new HealthResponse(
                "Health Good!~",
                Arrays.toString(environment.getActiveProfiles()),
                LocalDateTime.now()
        );

        return ResponseDto.ok(response);
    }
}
