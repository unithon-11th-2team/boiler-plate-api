package com.core.api.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.core.api.error.ErrorType;
import com.core.api.error.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3UploadService {
    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadSingleFile(MultipartFile file) {
        try {
            return uploadToS3(file, file.getOriginalFilename(), false);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InvalidRequestException(ErrorType.INVALID_REQUEST_ERROR);
        }
    }

    public PutObjectResult uploadToS3(String bucketName, File file) {
        return s3.putObject(new PutObjectRequest(bucketName, file.getName(), file)
                .withCannedAcl(CannedAccessControlList.BucketOwnerFullControl));
    }

    private String uploadToS3(MultipartFile file, String fileName, boolean isPrivate) throws Exception {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        byte[] resultByte = DigestUtils.md5(file.getBytes());
        String streamMD5 = new String(Base64.encodeBase64(resultByte));
        metadata.setContentMD5(streamMD5);

        s3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                .withCannedAcl(isPrivate ? CannedAccessControlList.Private : CannedAccessControlList.PublicRead));

        return s3.getUrl(bucketName, fileName).toString();
    }
}
