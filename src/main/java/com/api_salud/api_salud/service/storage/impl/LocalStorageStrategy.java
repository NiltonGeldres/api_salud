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
        return path;
    }

	@Override
	public String generarPresignedUrl(String rutaRelativa) {
		// TODO Auto-generated method stub
		return null;
	}
}

/*
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
    
    @Override
    public String getUrl(String path) {
        // Garantiza que la ruta comience con '/'
        String pathLimpio = path.startsWith("/") ? path : "/" + path;
        // Retorna la URL servida por WebMvcConfigurer para el iframe en React
        return "http://localhost:8080/archivos-locales" + pathLimpio;
    }    
}
*/