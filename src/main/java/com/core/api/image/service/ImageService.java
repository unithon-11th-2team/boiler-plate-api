package com.core.api.image.service;

import com.core.api.image.model.dto.ImageApiDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final S3UploadService s3UploadService;

    @Value("${cloud.aws.s3.url}")
    private String s3Url;
    
    public ImageApiDTO uploadImage(MultipartFile image) {
        var imageUrl = s3UploadService.uploadSingleFile(image);

        log.info("uploadImage / imageUrl {}", imageUrl);

        return new ImageApiDTO(imageUrl);
    }
}
