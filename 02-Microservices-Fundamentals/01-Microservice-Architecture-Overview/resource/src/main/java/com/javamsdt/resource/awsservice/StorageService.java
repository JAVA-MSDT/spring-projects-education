package com.javamsdt.resource.awsservice;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.javamsdt.resource.configuration.AwsS3Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final AmazonS3 amazonS3;
    private final AwsS3Config s3Config;

    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        log.info("Uploading file '{}' to bucket: '{}' ", fileName, s3Config.getBucketName());
        amazonS3.putObject(s3Config.getBucketName(), fileName, multipartFile.getInputStream(), objectMetadata);

        return s3Config.getS3EndpointUrl() + "/" + s3Config.getBucketName() + "/" + fileName;
    }

    public S3ObjectInputStream downloadFileFromS3Bucket(final String fileName) {
        log.info("Downloading file '{}' from bucket: '{}' ", fileName, s3Config.getBucketName());
        final S3Object s3Object = amazonS3.getObject(s3Config.getBucketName(), fileName);
        return s3Object.getObjectContent();
    }

    public List<S3ObjectSummary> listObjects() {
        log.info("Retrieving object summaries for bucket '{}'", s3Config.getBucketName());
        ObjectListing objectListing = amazonS3.listObjects(s3Config.getBucketName());
        return objectListing.getObjectSummaries();
    }
}
