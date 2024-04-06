package com.core.api.item.controller;

import com.core.api.auth.AuthUser;
import com.core.api.common.dto.ResponseDto;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.response.CommentSaveResponseDto;
import com.core.api.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "목탁 댓글 관리")
@RestController
@RequestMapping(path = "/api/v1/item-comment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemCommentController {
    private final ItemService itemService;

    @Operation(summary = "목탁 댓글 달기")
    @PostMapping
    public ResponseEntity<ResponseDto<CommentSaveResponseDto>> itemComment(
            AuthUser user,
            @RequestBody CommentSaveDto commentSaveDto
    ) {
        var response = itemService.itemComment(user.getId(), commentSaveDto);
        return ResponseDto.created(response);
    }

    @Operation(summary = "목탁 댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> itemCommentDelete(
            AuthUser user,
            @PathVariable Long commentId
    ) {
        itemService.itemCommentDelete(commentId, user.getId());
        return ResponseDto.noContent();
    }
}
