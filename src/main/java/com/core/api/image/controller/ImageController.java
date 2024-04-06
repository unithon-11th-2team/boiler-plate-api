package com.core.api.image.controller;

import com.core.api.common.dto.ResponseDto;
import com.core.api.image.model.dto.ImageApiDTO;
import com.core.api.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지 관리", description = "IMAGE API")
@RestController
@RequestMapping(path = "/api/v1/images", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "이미지 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<ImageApiDTO>> uploadImage(
            @RequestPart(name = "image") MultipartFile image
    ) {
        var response = imageService.uploadImage(image);
        return ResponseDto.created(response);
    }
}
