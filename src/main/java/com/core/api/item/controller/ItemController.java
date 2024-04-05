package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.CommentSaveResponseDto;
import com.core.api.item.dto.response.ItemSaveResponseDto;
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

    @Operation(summary = "목탁 던지기")
    @PostMapping()
    public ResponseEntity<ResponseDto<ItemSaveResponseDto>> itemSave(
            AuthUser user,
            @RequestBody ItemSaveDto itemSaveDto
            ) {
        var request = itemService.itemSave(user, itemSaveDto);
        return ResponseDto.created(request);
    }

    @Operation(summary = "목탁 생성 확인")
    @GetMapping()
    public ResponseEntity<ResponseDto<List<ItemSaveResponseDto>>> itemList(
            AuthUser user,
            BigDecimal latitude,
            BigDecimal longitude
    ) {
        var request = itemService.itemList(latitude, longitude);
        return ResponseDto.ok(request);
    }

    @Operation(summary = "목탁 댓글 달기")
    @PostMapping("/commentSave")
    public ResponseEntity<ResponseDto<CommentSaveResponseDto>> itemComment(
            AuthUser user,
            @RequestBody CommentSaveDto commentSaveDto
            ) {
        var request = itemService.itemComment(user.getId(), commentSaveDto);
        return ResponseDto.created(request);
    }
}
