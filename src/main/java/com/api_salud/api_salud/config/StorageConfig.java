package com.api_salud.api_salud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.storage")
public class StorageConfig {

    private String provider;
    private LocalConfig local;
    private PathConfig path;
    private R2Config r2;
    
    // Getters y Setters
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public LocalConfig getLocal() { return local; }
    public void setLocal(LocalConfig local) { this.local = local; }

    public PathConfig getPath() { return path; }
    public void setPath(PathConfig path) { this.path = path; }

    public R2Config getR2() { return r2; }
    public void setR2(R2Config r2) { this.r2 = r2; }

    // Clases estáticas internas
    public static class LocalConfig {
        private String rootPath;
        public String getRootPath() { return rootPath; }
        public void setRootPath(String rootPath) { this.rootPath = rootPath; }
    }

    public static class PathConfig {
        private String historias;
        public String getHistorias() { return historias; }
        public void setHistorias(String historias) { this.historias = historias; }
    }

    public static class R2Config {
        private String endpoint;
        private String accessKeyId;
        private String secretAccessKey;
        private String bucketName;
        private String publicUrlBase;

        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

        public String getAccessKeyId() { return accessKeyId; }
        public void setAccessKeyId(String accessKeyId) { this.accessKeyId = accessKeyId; }

        public String getSecretAccessKey() { return secretAccessKey; }
        public void setSecretAccessKey(String secretAccessKey) { this.secretAccessKey = secretAccessKey; }

        public String getBucketName() { return bucketName; }
        public void setBucketName(String bucketName) { this.bucketName = bucketName; }

        public String getPublicUrlBase() { return publicUrlBase; }
        public void setPublicUrlBase(String publicUrlBase) { this.publicUrlBase = publicUrlBase; }
    }

}

/*
package com.api_salud.api_salud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.storage")
public class StorageConfig {
    private String provider;
    private LocalConfig local;
    private PathConfig path;
    private GcsConfig gcs;
    
    // Getters y Setters
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public LocalConfig getLocal() { return local; }
    public void setLocal(LocalConfig local) { this.local = local; }

    public PathConfig getPath() { return path; }
    public void setPath(PathConfig path) { this.path = path; }

    public GcsConfig getGcs() { return gcs; }
    public void setGcs(GcsConfig gcs) { this.gcs = gcs; }    

    // Clases estáticas internas
    public static class LocalConfig {
        private String rootPath;
        public String getRootPath() { return rootPath; }
        public void setRootPath(String rootPath) { this.rootPath = rootPath; }
    }

    public static class PathConfig {
        private String historias;
        public String getHistorias() { return historias; }
        public void setHistorias(String historias) { this.historias = historias; }
    }
    
    public static class GcsConfig {
        private String bucketName;
        private String projectId;

        public String getBucketName() { return bucketName; }
        public void setBucketName(String bucketName) { this.bucketName = bucketName; }

        public String getProjectId() { return projectId; }
        public void setProjectId(String projectId) { this.projectId = projectId; }
    }
    
}*/