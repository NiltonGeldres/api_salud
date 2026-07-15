package com.api_salud.api_salud.service.storage.impl;

import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.StorageStrategy;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalStorageStrategy implements StorageStrategy {
    private final StorageConfig config;

    public LocalStorageStrategy(StorageConfig config) {
        this.config = config;
    }

    @Override
    public void save(String path, byte[] content) {
        try {
            // Unimos la raíz (D:/...) con la ruta relativa
            String fullPath = config.getLocal().getRootPath() + path;
            File file = new File(fullPath);
            file.getParentFile().mkdirs(); // Crea carpetas si no existen
            Files.write(Paths.get(fullPath), content);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando archivo localmente", e);
        }
    }
}