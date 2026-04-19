package com.api_salud.api_salud.entity;

import java.io.Serializable;
import java.util.Date;



public class PacienteEntity implements Serializable {
	 private static final long serialVersionUID = 1L;

	    // Identificadores y Seguridad
	    private Integer idPaciente;
	    private Integer idEntidad;
	    private Integer idUsuarioRegistro;
	    
	    // Documentos
	    private Integer idDocIdentidad;
	    private String nroDocumento;
	    private String nroHistoriaClinica;

	    // Nombres
	    private String apellidoPaterno;
	    private String apellidoMaterno;
	    private String primerNombre;
	    private String segundoNombre;
	    private String tercerNombre;

	    // Datos Personales y Salud
	    private String fechaNacimiento; // Formato YYYY-MM-DD
	    private Integer idTipoSexo;
	    private Integer idEstadoCivil;
	    private Integer idIdioma;
	    private Integer idEtnia;
	    private Integer idReligion;
	    private String grupoSanguineo;
	    private String factorRh;

	    // UBIGEO (Reemplaza las 12 columnas antiguas)
	    private String idUbigeoNacimiento;  // Coincide con idubigeonacimiento en SP
	    private String idUbigeoDomicilio;   // Coincide con idubigeodomicilio en SP
	    private String idUbigeoProcedencia; // Coincide con idubigeoprocedencia en SP
	    private String direccionDomicilio;

	    // Contacto y Familia
	    private String telefono;
	    private String email;
	    private String nombrePadre;
	    private String nombreMadre;
	    private Integer nroOrdenHijo;
	    private Integer idGradoInstruccion;
	    private Integer idTipoOcupacion;
	    private String observacion;

	    public PacienteEntity() {}

		public Integer getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(Integer idPaciente) {
			this.idPaciente = idPaciente;
		}

		public Integer getIdEntidad() {
			return idEntidad;
		}

		public void setIdEntidad(Integer idEntidad) {
			this.idEntidad = idEntidad;
		}

		public Integer getIdUsuarioRegistro() {
			return idUsuarioRegistro;
		}

		public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
			this.idUsuarioRegistro = idUsuarioRegistro;
		}

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

		public String getNroHistoriaClinica() {
			return nroHistoriaClinica;
		}

		public void setNroHistoriaClinica(String nroHistoriaClinica) {
			this.nroHistoriaClinica = nroHistoriaClinica;
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

		public String getDireccionDomicilio() {
			return direccionDomicilio;
		}

		public void setDireccionDomicilio(String direccionDomicilio) {
			this.direccionDomicilio = direccionDomicilio;
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

		public String getObservacion() {
			return observacion;
		}

		public void setObservacion(String observacion) {
			this.observacion = observacion;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}	

	    
}
