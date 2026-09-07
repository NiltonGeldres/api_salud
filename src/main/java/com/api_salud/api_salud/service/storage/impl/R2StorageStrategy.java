package com.api_salud.api_salud.service.storage.impl;

import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.StorageStrategy;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

public class R2StorageStrategy implements StorageStrategy {

    private final StorageConfig config;
    private final S3Client s3Client;

    public R2StorageStrategy(StorageConfig config) {
        this.config = config;

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                config.getR2().getAccessKeyId(),
                config.getR2().getSecretAccessKey()
        );

        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create(config.getR2().getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.US_EAST_1)
                .httpClientBuilder(UrlConnectionHttpClient.builder())
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
}