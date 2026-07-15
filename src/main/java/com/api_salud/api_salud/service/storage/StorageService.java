package com.api_salud.api_salud.service.storage;

import org.springframework.stereotype.Service;
import com.api_salud.api_salud.service.storage.impl.GcsStorageStrategy;
import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.impl.LocalStorageStrategy; // <--- ESTA ES LA QUE FALTA

@Service
public class StorageService {

    private final StorageConfig config;
    private final StorageStrategy strategy;

    public StorageService(StorageConfig config) {
        this.config = config;
        // Decisión en tiempo de ejecución
        if ("CLOUD".equals(config.getProvider())) {
            this.strategy = new GcsStorageStrategy(config);
        } else {
            this.strategy = new LocalStorageStrategy(config);
        }
    }

    public void guardar(String rutaRelativa, byte[] content) {
        // La ruta relativa (ej: /EMP01/historias_EMP01/...) 
        // se maneja igual para ambos, la estrategia decide cómo escribirla
        strategy.save(rutaRelativa, content);
    }
}