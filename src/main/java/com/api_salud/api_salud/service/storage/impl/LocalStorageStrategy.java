package com.api_salud.api_salud.service.storage.impl;

import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.StorageStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalStorageStrategy implements StorageStrategy {

    private final StorageConfig config;

    public LocalStorageStrategy(StorageConfig config) {
        this.config = config;
    }

    @Override
    public void save(String path, byte[] content) {
        try {
            File file = new File(config.getLocal().getRootPath(), path);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(content);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo localmente: " + e.getMessage(), e);
        }
    }

    @Override
    public String getUrl(String path) {
        String objectName = path.startsWith("/") ? path.substring(1) : path;
        return "/archivos-locales/" + objectName;
    }

    @Override
    public String generarPresignedUrl(String path) {
        return getUrl(path);
    }

    @Override
    public String generarPresignedUrlSubida(String path) {
        return getUrl(path);
    }
}