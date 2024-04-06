package com.core.api.rank.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.rank.dto.RankDto;
import com.core.api.rank.service.RankService;
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

@Tag(name = "랭킹 관리")
@RestController
@RequestMapping(path = "/api/v1/ranks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;

    /**
     * - type : paradise, hell
     */
    @Operation(summary = "극락 및 나락 지역 랭킹 조회")
    @GetMapping("/address")
    public ResponseEntity<ResponseDto<List<RankDto>>> getRanks(
            AuthUser user,
            @RequestParam(value = "type", required = false, defaultValue = "paradise") String type
    ) {
        return ResponseDto.ok(rankService.getRanks(type));
    }
}
