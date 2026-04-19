package com.api_salud.api_salud.dto;



public class PacienteDto {
	   private static final long serialVersionUID = 1L;

	    // Identificadores y Documentos
	    private Integer idPaciente;
	    private String nroHistoriaClinica;
	    private Integer idDocIdentidad;
	    private String nroDocumento;

	    // Nombres
	    private String apellidoPaterno;
	    private String apellidoMaterno;
	    private String primerNombre;
	    private String segundoNombre;
	    private String tercerNombre;

	    // Datos Personales
	    private String fechaNacimiento; // Se puede usar String o LocalDate según tu Mapper
	    private Integer idTipoSexo;
	    private Integer idEstadoCivil;
	    private Integer idIdioma;
	    private Integer idEtnia;
	    private Integer idReligion;

	    // Contacto y Ubicación
	    private String telefono;
	    private String email;
	    private String direccionDomicilio;
	    private String idUbigeoNacimiento;
	    private String idUbigeoDomicilio;
	    private String idUbigeoProcedencia;

	    // Familia y Otros
	    private String nombrePadre;
	    private String nombreMadre;
	    private Integer nroOrdenHijo;
	    private Integer idGradoInstruccion;
	    private Integer idTipoOcupacion;
	    private String grupoSanguineo;
	    private String factorRh;
	    private String observacion;

	    // Auditoría y Multitenant
	    private Integer idUsuario;
	    private Integer idEntidad;
	    private String fechaRegistro;

	    // Constructor vacío (Requerido para Frameworks)
	    public PacienteDto() {}

		public Integer getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(Integer idPaciente) {
			this.idPaciente = idPaciente;
		}

		public String getNroHistoriaClinica() {
			return nroHistoriaClinica;
		}

		public void setNroHistoriaClinica(String nroHistoriaClinica) {
			this.nroHistoriaClinica = nroHistoriaClinica;
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

		public Integer getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}

		public Integer getIdEntidad() {
			return idEntidad;
		}

		public void setIdEntidad(Integer idEntidad) {
			this.idEntidad = idEntidad;
		}

		public String getFechaRegistro() {
			return fechaRegistro;
		}

		public void setFechaRegistro(String fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	    
}
