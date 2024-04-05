package com.core.api.user.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.user.dto.UserSignRequest;
import com.core.api.user.dto.UserSignResponse;
import com.core.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 관리")
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/sign")
    public ResponseEntity<ResponseDto<UserSignResponse>> sign(
            @RequestBody UserSignRequest request
    ) {
        var user = userService.sign(request);
        return ResponseDto.created(user);
    }
}
