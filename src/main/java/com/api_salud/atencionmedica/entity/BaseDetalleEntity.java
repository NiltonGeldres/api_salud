package com.api_salud.atencionmedica.entity;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Clase base para todas las entidades de detalle que tienen campos comunes.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDetalleEntity {
    
    protected Long idAtencion;
    protected Integer idUsuario;
    protected OffsetDateTime tsRegistro;
	public Long getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public OffsetDateTime getTsRegistro() {
		return tsRegistro;
	}
	public void setTsRegistro(OffsetDateTime tsRegistro) {
		this.tsRegistro = tsRegistro;
	}
    
    

}