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
     * @param hcPaciente Historia Clínica del paciente ({paciente})
     * @param idAtencion ID de la atención médica ({atencion})
     * @param tipoDoc Tipo: historia, receta, ordenes, indicaciones ({tipo})
     * @param esFirmado true para archivo final firmado, false para borrador
     */
    public String construirRutaRelativa(Integer idEntidad, String hcPaciente, Long idAtencion, String tipoDoc, boolean esFirmado) {    
        String plantilla = esFirmado ? config.getPath().getFirmado() : config.getPath().getBorrador();
        String estado = esFirmado ? "firmado" : "borrador";
        return plantilla
                .replace("{empresa}", String.valueOf(idEntidad != null ? idEntidad : 0))
                .replace("{paciente}", (hcPaciente != null && !hcPaciente.trim().isEmpty()) ? hcPaciente : "SIN_HC")
                .replace("{atencion}", String.valueOf(idAtencion))
                .replace("{tipo}", tipoDoc)
                .replace("{estado}", estado);
    }

    /**
     * Construye dinámicamente la ruta plantilla para almacenar o consultar el logo de una entidad.
     */
    public String construirRutaLogo(Integer idEntidad) {
        String plantilla = config.getPath().getLogo();
        return plantilla.replace("{empresa}", String.valueOf(idEntidad != null ? idEntidad : 0));
    }

    /**
     * Convierte una ruta relativa o clave de logo en su URL pública accesible para el PDF/Frontend.
     */
    public String resolverUrlPublicaLogo(String rutaRelativaLogo) {
        if (rutaRelativaLogo == null || rutaRelativaLogo.trim().isEmpty()) {
            return null;
        }

        // Si la ruta ya viene como URL absoluta (http/https), la devuelve intacta
        if (rutaRelativaLogo.startsWith("http://") || rutaRelativaLogo.startsWith("https://")) {
            return rutaRelativaLogo;
        }

        // Delega la construcción de la URL completa a la estrategia activa (R2 o Local)
        return obtenerUrlPublica(rutaRelativaLogo);
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