package com.api_salud.api_salud.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SecurityUtils {

    @Value("${app.secret-key:IgmSalud2026_ClaveSecretaDeInmutabilidad!_873398}")
    private String secretKey;

    /**
     * Genera un Hash SHA-256 combinando el JSON enriquecido, la clave secreta
     * del servidor y el ID de la atención médica para garantizar la integridad.
     */
    public String generarHashIntegridad(String jsonPayload, Long idAtencion) {
        try {
            String dataToHash = jsonPayload + secretKey + idAtencion;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash SHA-256", e);
        }
    }
}
