package com.api_salud.atencionmedica.response;
import com.api_salud.atencionmedica.entity.AtencionMedica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de salida estándar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtencionMedicaResponse {
    private String mensaje;
    private AtencionMedica atencion;

}
