package com.core.api.item.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.item.service.ItemService;
import com.core.api.user.dto.UserSignRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "목탁 관리")
@RestController
@RequestMapping(path = "/api/v1/item", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "목탁 등록")
    @PostMapping("/itemSave")
    public ResponseEntity<ResponseDto<Object>> itemSave(

    ) {
        return ResponseDto.created(null);
    }
}
