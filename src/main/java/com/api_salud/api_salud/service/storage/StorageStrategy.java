package com.api_salud.api_salud.service.storage;

public interface StorageStrategy {
    void save(String path, byte[] content);
    byte[] read(String path);    
    String getUrl(String path);
    String generarPresignedUrl(String rutaRelativa);
    String generarPresignedUrlSubida(String rutaRelativa); // <-- NUEVO
    
}
