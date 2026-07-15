package com.api_salud.api_salud.service.storage.impl;

import com.api_salud.api_salud.config.StorageConfig;
import com.api_salud.api_salud.service.storage.StorageStrategy;



public class GcsStorageStrategy implements StorageStrategy {

    private final StorageConfig config; // Campo para guardar la config

    // ESTE ES EL CONSTRUCTOR QUE FALTA
    public GcsStorageStrategy(StorageConfig config) {
        this.config = config;
    }

    @Override
    public void save(String path, byte[] content) {
        // Aquí irá la lógica de GCS más adelante
    }
}