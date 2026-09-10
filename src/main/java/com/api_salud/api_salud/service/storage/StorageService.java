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

    /**
     * Construye dinámicamente la ruta en el bucket reemplazando variables.
     * @param idEntidad ID de la empresa/entidad ({empresa})
     * @param idPaciente ID del paciente ({paciente})
     * @param idAtencion ID de la atención médica ({atencion})
     * @param tipoDocumento Tipo: historia, receta, ordenes, indicaciones ({tipo})
     * @param esFirmado true para archivo final firmado, false para borrador
     */
    public String construirRutaRelativa(Integer idEntidad, String hcPaciente, Long idAtencion, String tipoDoc, boolean esFirmado){    
        String plantilla = esFirmado ? config.getPath().getFirmado() : config.getPath().getBorrador();
    	String estado = esFirmado ? "firmado" : "borrador";
        return plantilla
                .replace("{empresa}", String.valueOf(idEntidad))
                .replace("{paciente}", (hcPaciente != null && !hcPaciente.trim().isEmpty()) ? hcPaciente : "SIN_HC")
                .replace("{atencion}", String.valueOf(idAtencion))
                .replace("{tipo}", tipoDoc)
                .replace("{estado}", estado);
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

    public String generarPresignedUrlSubida(String rutaRelativa) {
        return strategy.generarPresignedUrlSubida(rutaRelativa);
    }
}