package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "목탁 치기 관리")
@RestController
@RequestMapping(path = "/api/v1/item-like", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemLikeController {
    private final ItemService itemService;

    @Operation(summary = "목탁 치기")
    @PostMapping("/{id}")
    public ResponseEntity<Void> itemLike(
            AuthUser user,
            @PathVariable Long id
    ) {
        itemService.itemLike(user.getId(), id);
        return ResponseDto.noContent();
    }

    @Operation(summary = "목탁 치기 취소")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> itemLikeCancel(
            AuthUser user,
            @PathVariable Long id
    ) {
        itemService.itemLikeCancel(user.getId(), id);
        return ResponseDto.noContent();
    }

}

