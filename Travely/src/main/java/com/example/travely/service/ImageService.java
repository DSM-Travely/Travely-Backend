package com.example.travely.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.travely.dto.ImageListResponse;
import com.example.travely.exception.ImageUploadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public ImageListResponse uploadImages(List<MultipartFile> files) {
        List<String> fileUrls = files.stream().map(file ->
                {
                    String fileName = createFileName(file.getOriginalFilename());
                    ObjectMetadata objectMetadata = new ObjectMetadata();
                    objectMetadata.setContentLength(file.getSize());
                    objectMetadata.setContentType(file.getContentType());
                    try (InputStream inputStream = file.getInputStream()) {
                        amazonS3Client.putObject(bucket, fileName, inputStream, objectMetadata);
                        return amazonS3Client.getResourceUrl(bucket, fileName);
                    } catch (IOException e) {
                        throw new ImageUploadFailedException();
                    }
                }
        ).toList();

        return ImageListResponse.builder()
                .imageNames(fileUrls)
                .build();
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
