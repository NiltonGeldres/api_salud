package com.api_salud.api_salud.service.storage;

public interface StorageStrategy {
    void save(String path, byte[] content);
}
