package com.api_salud.api_salud.service.storage.impl;

import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.StorageStrategy;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URI;
import java.time.Duration;

public class R2StorageStrategy implements StorageStrategy {

    private final StorageConfig config;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;     

    public R2StorageStrategy(StorageConfig config) {
        this.config = config;

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                config.getR2().getAccessKeyId(),
                config.getR2().getSecretAccessKey()
        );

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
        URI endpointUri = URI.create(config.getR2().getEndpoint());

        this.s3Client = S3Client.builder()
                .endpointOverride(endpointUri)
                .credentialsProvider(credentialsProvider)
                .region(Region.US_EAST_1)
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .build();

        this.s3Presigner = S3Presigner.builder()
                .endpointOverride(endpointUri)
                .credentialsProvider(credentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public void save(String path, byte[] content) {
        String objectName = path.startsWith("/") ? path.substring(1) : path;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(config.getR2().getBucketName())
                .key(objectName)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));
    }

    @Override
    public String getUrl(String path) {
        String objectName = path.startsWith("/") ? path.substring(1) : path;
        String baseUrl = config.getR2().getPublicUrlBase();

        if (baseUrl != null && baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        return baseUrl + "/" + objectName;
    }

    @Override
    public String generarPresignedUrl(String path) {
        if (path == null || path.trim().isEmpty()) {
            return null;
        }
        String objectName = path.startsWith("/") ? path.substring(1) : path;

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(config.getR2().getBucketName())
                .key(objectName)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }

    @Override
    public String generarPresignedUrlSubida(String path) {
        if (path == null || path.trim().isEmpty()) {
            return null;
        }
        String objectName = path.startsWith("/") ? path.substring(1) : path;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(config.getR2().getBucketName())
                .key(objectName)
                .contentType("application/pdf")
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(putObjectRequest)
                .build();

        return s3Presigner.presignPutObject(presignRequest).url().toString();
    }
}