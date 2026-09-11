package com.api_salud.api_salud.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Service
public class TenantLogoCacheService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "tenantLogos", key = "#logoUrl", unless = "#result == null")
    public String obtenerLogoBase64(String logoUrl) {
        try {
            byte[] imageBytes = restTemplate.getForObject(logoUrl, byte[].class);
            if (imageBytes != null && imageBytes.length > 0) {
                return Base64.getEncoder().encodeToString(imageBytes);
            }
        } catch (Exception e) {
            // Log de advertencia si la URL falla
            System.err.println("Error al cargar el logo desde R2: " + e.getMessage());
        }
        return null;
    }
}