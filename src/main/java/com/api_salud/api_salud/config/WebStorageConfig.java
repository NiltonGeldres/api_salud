package com.api_salud.api_salud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebStorageConfig implements WebMvcConfigurer {

    private final StorageConfig storageConfig;

    public WebStorageConfig(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = storageConfig.getLocal().getRootPath();
        if (rootPath != null && !rootPath.trim().isEmpty()) {
            // Normalización de ruta a formato URI de archivo estático
            File rootDir = new File(rootPath);
            String location = rootDir.toURI().toString();

            registry.addResourceHandler("/archivos-locales/**")
                    .addResourceLocations(location);
        }
    }
}