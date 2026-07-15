package com.api_salud.api_salud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.storage")
public class StorageConfig {
    private String provider;
    private LocalConfig local;
    private PathConfig path;

    // Getters y Setters
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public LocalConfig getLocal() { return local; }
    public void setLocal(LocalConfig local) { this.local = local; }

    public PathConfig getPath() { return path; }
    public void setPath(PathConfig path) { this.path = path; }

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
}