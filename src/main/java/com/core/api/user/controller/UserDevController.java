package com.core.api.user.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.user.dto.UserInfoDevResponse;
import com.core.api.user.service.UserDevService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "사용자 관련 개발 전용 API, (테스트 및 개발 편의 용도로 사용)")
@RestController
@RequestMapping(path = "/api/v1/dev/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserDevController {
    private final UserDevService userDevService;

    /**
     * **테스트 및 개발 편의 용도의 API, 실제 서비스에서 사용은 금지**
     * <p>
     * Request Param이 없다면, 전체 데이터를 제공
     */
    @Operation(summary = "유저 정보 전체 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<List<UserInfoDevResponse>>> getAll(
            @RequestParam List<Long> ids
    ) {
        var response = userDevService.getAll(ids);
        return ResponseDto.ok(response);
    }
}
