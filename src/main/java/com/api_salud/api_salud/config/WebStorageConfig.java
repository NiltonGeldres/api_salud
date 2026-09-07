package com.api_salud.api_salud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebStorageConfig implements WebMvcConfigurer {

    private final StorageConfig storageConfig;

    public WebStorageConfig(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = storageConfig.getLocal().getRootPath(); // "D:/ARCHIVO_DIGITAL"
        String resourcePath = "file:///" + rootPath.replace("\\", "/").replaceAll("/$", "") + "/";
        
        registry.addResourceHandler("/archivos-locales/**")
                .addResourceLocations(resourcePath);
    }
}