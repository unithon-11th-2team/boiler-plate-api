package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "목탁 댓글 좋아요")
@RestController
@RequestMapping(path = "/api/v1/comment-likes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemCommentLikeController {
    private final ItemService itemService;

    @Operation(summary = "목탁 댓글 좋아요 누르기")
    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto<Integer>> itemCommentLike(
            AuthUser user,
            @PathVariable Long id
    ) {
        return ResponseDto.ok(itemService.itemCommentLike(user.getId(), id));
    }
    @Operation(summary = "목탁 댓글 좋아요 취소")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Integer>> itemCommentLikeCancel(
            AuthUser user,
            @PathVariable Long id
    ) {
        return ResponseDto.ok(itemService.itemCommentLikeCancel(user.getId(), id));
    }
}
