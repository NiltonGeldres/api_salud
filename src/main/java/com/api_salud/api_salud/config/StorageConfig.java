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
    
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public LocalConfig getLocal() { return local; }
    public void setLocal(LocalConfig local) { this.local = local; }

    public PathConfig getPath() { return path; }
    public void setPath(PathConfig path) { this.path = path; }

    public R2Config getR2() { return r2; }
    public void setR2(R2Config r2) { this.r2 = r2; }

    public static class LocalConfig {
        private String rootPath;
        public String getRootPath() { return rootPath; }
        public void setRootPath(String rootPath) { this.rootPath = rootPath; }
    }

    public static class PathConfig {
        private String borrador;
        private String firmado;
        private String logo; 

        public String getBorrador() { return borrador; }
        public void setBorrador(String borrador) { this.borrador = borrador; }

        public String getFirmado() { return firmado; }
        public void setFirmado(String firmado) { this.firmado = firmado; }
        
        public String getLogo() { return logo; }
        public void setLogo(String logo) { this.logo = logo; }
        
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