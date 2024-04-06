package com.core.api.rank.controller;

import com.core.api.rank.service.RankService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "랭킹 관리")
@RestController
@RequestMapping(path = "/api/v1/ranks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;
}
