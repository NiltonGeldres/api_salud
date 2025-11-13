package com.api_salud.atencionmedica.request;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class AtencionMedicaRequest {
    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El ID de la cuenta de atención es obligatorio")
    private Integer idCuentaAtencion;
    
    @NotNull(message = "El ID del servicio es obligatorio")
    private Integer idServicio;
    
    @NotNull(message = "El ID del médico de ingreso es obligatorio")
    private Integer idMedicoIngreso;
    
    @NotNull(message = "El ID del estado de atención es obligatorio")
    private Integer idEstadoAtencion;
    
    @NotNull(message = "La fecha de ingreso es obligatoria")
    private OffsetDateTime tsIngreso;
    
    @NotNull(message = "El ID de usuario de registro es obligatorio")
    private Integer idUsuarioRegistro;
    
    private String origenRegistroUsuario; // Puede ser nulo/por defecto

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdCuentaAtencion() {
		return idCuentaAtencion;
	}

	public void setIdCuentaAtencion(Integer idCuentaAtencion) {
		this.idCuentaAtencion = idCuentaAtencion;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public Integer getIdMedicoIngreso() {
		return idMedicoIngreso;
	}

	public void setIdMedicoIngreso(Integer idMedicoIngreso) {
		this.idMedicoIngreso = idMedicoIngreso;
	}

	public Integer getIdEstadoAtencion() {
		return idEstadoAtencion;
	}

	public void setIdEstadoAtencion(Integer idEstadoAtencion) {
		this.idEstadoAtencion = idEstadoAtencion;
	}

	public OffsetDateTime getTsIngreso() {
		return tsIngreso;
	}

	public void setTsIngreso(OffsetDateTime tsIngreso) {
		this.tsIngreso = tsIngreso;
	}

	public Integer getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}

	public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}

	public String getOrigenRegistroUsuario() {
		return origenRegistroUsuario;
	}

	public void setOrigenRegistroUsuario(String origenRegistroUsuario) {
		this.origenRegistroUsuario = origenRegistroUsuario;
	}
    
    

}
