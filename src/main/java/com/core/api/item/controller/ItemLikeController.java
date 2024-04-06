package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.request.ItemLikeSaveDto;
import com.core.api.item.dto.response.CommentSaveResponseDto;
import com.core.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "목탁 관리")
@RestController
@RequestMapping(path = "/api/v1/item-like", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemLikeController {
    private final ItemService itemService;

    @Operation(summary = "목탁 치기")
    @PostMapping("/itemLike")
    public ResponseEntity<Void> itemLike(
            AuthUser user,
            @RequestBody ItemLikeSaveDto itemLikeSaveDto
            ) {
        itemService.itemLike(user.getId(), itemLikeSaveDto.getItemId());
        return ResponseDto.noContent();
    }

    @Operation(summary = "목탁 치기 취소")
    @DeleteMapping("/itemLike")
    public ResponseEntity<Void> itemLikeCancel(
            AuthUser user,
            Long itemId
    ) {
        itemService.itemLikeCancel(user.getId(), itemId);
        return ResponseDto.noContent();
    }

}

