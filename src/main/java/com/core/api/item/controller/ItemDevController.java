package com.core.api.item.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.item.dto.response.ItemDevResponse;
import com.core.api.item.service.ItemDevService;
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

@Tag(name = "목탁 관련 개발 전용 API, (테스트 및 개발 편의 용도로 사용")
@RestController
@RequestMapping(path = "/api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemDevController {
    private final ItemDevService itemDevService;

    /**
     * **테스트 및 개발 편의 용도의 API, 실제 서비스에서 사용은 금지**
     * <p>
     * Request Param이 없다면, 전체 데이터를 제공
     */
    @Operation(summary = "목탁 정보 전체 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<List<ItemDevResponse>>> getAll(
            @RequestParam List<Long> ids
    ) {
        var response = itemDevService.getAll(ids);
        return ResponseDto.ok(response);
    }
}
