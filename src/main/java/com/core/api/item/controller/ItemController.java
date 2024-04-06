package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.CommentSaveResponseDto;
import com.core.api.item.dto.response.ItemSaveResponseDto;
import com.core.api.item.dto.response.MyItemResponse;
import com.core.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "목탁 관리")
@RestController
@RequestMapping(path = "/api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "목탁 생성")
    @PostMapping
    public ResponseEntity<ResponseDto<ItemSaveResponseDto>> itemSave(
            AuthUser user,
            @RequestBody ItemSaveDto itemSaveDto
    ) {
        var response = itemService.itemSave(user, itemSaveDto);
        return ResponseDto.created(response);
    }

    @Operation(summary = "특정 범위안에 있는 목탁 조회 100미터")
    @GetMapping
    public ResponseEntity<ResponseDto<List<ItemSaveResponseDto>>> itemList(
            AuthUser user,
            BigDecimal latitude,
            BigDecimal longitude
    ) {
        var response = itemService.itemList(latitude, longitude);
        return ResponseDto.ok(response);
    }

    /**
     * 내가 생성한 전체 아이템 정보를 제공한다
     */
    @Operation(summary = "내가 생성한 목탁 정보 조회")
    @GetMapping("/my-items")
    public ResponseEntity<ResponseDto<List<MyItemResponse>>> getAllMyItems(AuthUser user) {
        var response = itemService.getAllMyItems(user);
        return ResponseDto.ok(response);
    }



}
