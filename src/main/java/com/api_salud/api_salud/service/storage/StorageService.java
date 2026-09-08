package com.api_salud.api_salud.service.storage;

import org.springframework.stereotype.Service;
import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.impl.LocalStorageStrategy;
import com.api_salud.api_salud.service.storage.impl.R2StorageStrategy;

@Service
public class StorageService {

    private final StorageConfig config;
    private final StorageStrategy strategy;

    public StorageService(StorageConfig config) {
        this.config = config;
        
        String provider = config.getProvider();
        System.out.println("=== ESTRATEGIA DE ALMACENAMIENTO SELECCIONADA: " + provider + " ===");

        if ("CLOUD".equalsIgnoreCase(provider) || "R2".equalsIgnoreCase(provider)) {
            System.out.println("=== USANDO R2 STORAGE STRATEGY ===");
            this.strategy = new R2StorageStrategy(config);
        } else {
            System.out.println("=== USANDO LOCAL STORAGE STRATEGY ===");
            this.strategy = new LocalStorageStrategy(config);
        }
    }

    public void guardar(String rutaRelativa, byte[] content) {
        strategy.save(rutaRelativa, content);
    }

    public String obtenerUrlPublica(String rutaRelativa) {
        return strategy.getUrl(rutaRelativa);
    }
    

    public String generarPresignedUrl(String rutaRelativa) {
        return strategy.generarPresignedUrl(rutaRelativa);
    }
}