package com.api_salud.api_salud.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PacienteRequest {


    private static final long serialVersionUID = 1L;

    // Documentos e Identidad
    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer idDocIdentidad;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 8, max = 20, message = "El documento debe tener entre 8 y 20 caracteres")
    private String nroDocumento;

    // Nombres
    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    private String apellidoMaterno;

    @NotBlank(message = "El primer nombre es obligatorio")
    private String primerNombre;

    private String segundoNombre;
    private String tercerNombre;

    // Datos Personales
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String fechaNacimiento; // Formato YYYY-MM-DD para el SP

    private Integer idTipoSexo;
    private Integer idEstadoCivil;
    private Integer idIdioma;
    private Integer idEtnia;
    private Integer idReligion;

    // Contacto y Ubicación
    private String telefono;

    @Email(message = "El formato del email no es válido")
    private String email;

    private String direccionDomicilio;
    private String idUbigeoNacimiento;
    private String idUbigeoDomicilio;
    private String idUbigeoProcedencia;

    // Familia y Salud
    private String nombrePadre;
    private String nombreMadre;
    private Integer nroOrdenHijo;
    private Integer idGradoInstruccion;
    private Integer idTipoOcupacion;
    private String grupoSanguineo;
    private String factorRh;
    private String observacion;

    // Multitenant y Seguridad (Si el SignUp los requiere)
    @NotNull(message = "La entidad es obligatoria")
    private Integer idEntidad;
    
    private String password; // Para crear el usuario simultáneamente

	public Integer getIdDocIdentidad() {
		return idDocIdentidad;
	}

	public void setIdDocIdentidad(Integer idDocIdentidad) {
		this.idDocIdentidad = idDocIdentidad;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTercerNombre() {
		return tercerNombre;
	}

	public void setTercerNombre(String tercerNombre) {
		this.tercerNombre = tercerNombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getIdTipoSexo() {
		return idTipoSexo;
	}

	public void setIdTipoSexo(Integer idTipoSexo) {
		this.idTipoSexo = idTipoSexo;
	}

	public Integer getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public Integer getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}

	public Integer getIdEtnia() {
		return idEtnia;
	}

	public void setIdEtnia(Integer idEtnia) {
		this.idEtnia = idEtnia;
	}

	public Integer getIdReligion() {
		return idReligion;
	}

	public void setIdReligion(Integer idReligion) {
		this.idReligion = idReligion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	public String getIdUbigeoNacimiento() {
		return idUbigeoNacimiento;
	}

	public void setIdUbigeoNacimiento(String idUbigeoNacimiento) {
		this.idUbigeoNacimiento = idUbigeoNacimiento;
	}

	public String getIdUbigeoDomicilio() {
		return idUbigeoDomicilio;
	}

	public void setIdUbigeoDomicilio(String idUbigeoDomicilio) {
		this.idUbigeoDomicilio = idUbigeoDomicilio;
	}

	public String getIdUbigeoProcedencia() {
		return idUbigeoProcedencia;
	}

	public void setIdUbigeoProcedencia(String idUbigeoProcedencia) {
		this.idUbigeoProcedencia = idUbigeoProcedencia;
	}

	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	public Integer getNroOrdenHijo() {
		return nroOrdenHijo;
	}

	public void setNroOrdenHijo(Integer nroOrdenHijo) {
		this.nroOrdenHijo = nroOrdenHijo;
	}

	public Integer getIdGradoInstruccion() {
		return idGradoInstruccion;
	}

	public void setIdGradoInstruccion(Integer idGradoInstruccion) {
		this.idGradoInstruccion = idGradoInstruccion;
	}

	public Integer getIdTipoOcupacion() {
		return idTipoOcupacion;
	}

	public void setIdTipoOcupacion(Integer idTipoOcupacion) {
		this.idTipoOcupacion = idTipoOcupacion;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getFactorRh() {
		return factorRh;
	}

	public void setFactorRh(String factorRh) {
		this.factorRh = factorRh;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
