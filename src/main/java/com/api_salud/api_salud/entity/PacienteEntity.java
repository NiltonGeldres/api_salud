package com.api_salud.api_salud.entity;

import java.util.Date;
/*
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
*/
public class PacienteEntity {
	

//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    @Column(name = "idpaciente")
	    private Integer idPaciente;

//	    @Column(name = "nrohistoriaclinica")
	    private String nroHistoriaClinica;

//	    @Column(name = "nrodocumento")
	    private String nroDocumento;

//	    @Column(name = "apellidopaterno")
	    private String apellidoPaterno;

//	    @Column(name = "apellidomaterno")
	    private String apellidoMaterno;

//	    @Column(name = "primernombre")
	    private String primerNombre;

//	    @Column(name = "segundonombre")
	    private String segundoNombre;

//	    @Column(name = "tercernombre")
	    private String tercerNombre;

//	    @Column(name = "fechanacimiento")
	    private String fechaNacimiento;

//	    @Column(name = "telefono")
	    private String telefono;

//	    @Column(name = "email")
	    private String email;

//	    @Column(name = "direcciondomicilio")
	    private String direccionDomicilio;

//	    @Column(name = "nombrepadre")
	    private String nombrePadre;

//	    @Column(name = "nombremadre")
	    private String nombreMadre;

//	    @Column(name = "observacion")
	    private String observacion;

//	    @Column(name = "gruposanguineo")
	    private String grupoSanguineo;

//	    @Column(name = "factorrh")
	    private String factorRh;

//	    @Column(name = "idtiposexo")
	    private Integer idTipoSexo;

//	    @Column(name = "idgradoinstruccion")
	    private Integer idGradoInstruccion;

	    //	    @Column(name = "idestadocivil")
	    private Integer idEstadoCivil;

	    //	    @Column(name = "iddocidentidad")
	    private Integer idDocIdentidad;

	    //	    @Column(name = "idtipoocupacion")
	    private Integer idTipoOcupacion;

	    //	    @Column(name = "nroordenhijo")
	    private Integer nroOrdenHijo;

	    //	    @Column(name = "ididioma")
	    private Integer idIdioma;

	    //	    @Column(name = "idetnia")
	    private Integer idEtnia;

	    //	    @Column(name = "idreligion")
	    private Integer idReligion;

	    //	    @Column(name = "idprocedencia")
	    private Integer idProcedencia;

	    //	    @Column(name = "idpaisdomicilio")
	    private Integer idPaisDomicilio;

	    //	    @Column(name = "idpaisprocedencia")
	    private Integer idPaisProcedencia;

	    //	    @Column(name = "idpaisnacimiento")
	    private Integer idPaisNacimiento;

	    //	    @Column(name = "iddepartamentoprocedencia")
	    private Integer idDepartamentoProcedencia;

	    //	    @Column(name = "iddepartamentodomicilio")
	    private Integer idDepartamentoDomicilio;

	    //	    @Column(name = "iddepartamentonacimiento")
	    private Integer idDepartamentoNacimiento;

	    //	    @Column(name = "iddistritoprocedencia")
	    private Integer idDistritoProcedencia;

	    //	    @Column(name = "iddistritodomicilio")
	    private Integer idDistritoDomicilio;

	    //	    @Column(name = "iddistritonacimiento")
	    private Integer idDistritoNacimiento;

	    //	    @Column(name = "idcentropobladoprocedencia")
	    private Integer idCentroPobladoProcedencia;

	    //	    @Column(name = "idcentropobladodomicilio")
	    private Integer idCentroPobladoDomicilio;

	    //	    @Column(name = "idcentropobladonacimiento")
	    private Integer idCentroPobladoNacimiento;

//    @Column(name = "idusuario")
	    private Integer idUsuario;

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

		public String getObservacion() {
			return observacion;
		}

		public void setObservacion(String observacion) {
			this.observacion = observacion;
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

		public Integer getIdTipoSexo() {
			return idTipoSexo;
		}

		public void setIdTipoSexo(Integer idTipoSexo) {
			this.idTipoSexo = idTipoSexo;
		}

		public Integer getIdGradoInstruccion() {
			return idGradoInstruccion;
		}

		public void setIdGradoInstruccion(Integer idGradoInstruccion) {
			this.idGradoInstruccion = idGradoInstruccion;
		}

		public Integer getIdEstadoCivil() {
			return idEstadoCivil;
		}

		public void setIdEstadoCivil(Integer idEstadoCivil) {
			this.idEstadoCivil = idEstadoCivil;
		}

		public Integer getIdDocIdentidad() {
			return idDocIdentidad;
		}

		public void setIdDocIdentidad(Integer idDocIdentidad) {
			this.idDocIdentidad = idDocIdentidad;
		}

		public Integer getIdTipoOcupacion() {
			return idTipoOcupacion;
		}

		public void setIdTipoOcupacion(Integer idTipoOcupacion) {
			this.idTipoOcupacion = idTipoOcupacion;
		}

		public Integer getNroOrdenHijo() {
			return nroOrdenHijo;
		}

		public void setNroOrdenHijo(Integer nroOrdenHijo) {
			this.nroOrdenHijo = nroOrdenHijo;
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

		public Integer getIdProcedencia() {
			return idProcedencia;
		}

		public void setIdProcedencia(Integer idProcedencia) {
			this.idProcedencia = idProcedencia;
		}

		public Integer getIdPaisDomicilio() {
			return idPaisDomicilio;
		}

		public void setIdPaisDomicilio(Integer idPaisDomicilio) {
			this.idPaisDomicilio = idPaisDomicilio;
		}

		public Integer getIdPaisProcedencia() {
			return idPaisProcedencia;
		}

		public void setIdPaisProcedencia(Integer idPaisProcedencia) {
			this.idPaisProcedencia = idPaisProcedencia;
		}

		public Integer getIdPaisNacimiento() {
			return idPaisNacimiento;
		}

		public void setIdPaisNacimiento(Integer idPaisNacimiento) {
			this.idPaisNacimiento = idPaisNacimiento;
		}

		public Integer getIdDepartamentoProcedencia() {
			return idDepartamentoProcedencia;
		}

		public void setIdDepartamentoProcedencia(Integer idDepartamentoProcedencia) {
			this.idDepartamentoProcedencia = idDepartamentoProcedencia;
		}

		public Integer getIdDepartamentoDomicilio() {
			return idDepartamentoDomicilio;
		}

		public void setIdDepartamentoDomicilio(Integer idDepartamentoDomicilio) {
			this.idDepartamentoDomicilio = idDepartamentoDomicilio;
		}

		public Integer getIdDepartamentoNacimiento() {
			return idDepartamentoNacimiento;
		}

		public void setIdDepartamentoNacimiento(Integer idDepartamentoNacimiento) {
			this.idDepartamentoNacimiento = idDepartamentoNacimiento;
		}

		public Integer getIdDistritoProcedencia() {
			return idDistritoProcedencia;
		}

		public void setIdDistritoProcedencia(Integer idDistritoProcedencia) {
			this.idDistritoProcedencia = idDistritoProcedencia;
		}

		public Integer getIdDistritoDomicilio() {
			return idDistritoDomicilio;
		}

		public void setIdDistritoDomicilio(Integer idDistritoDomicilio) {
			this.idDistritoDomicilio = idDistritoDomicilio;
		}

		public Integer getIdDistritoNacimiento() {
			return idDistritoNacimiento;
		}

		public void setIdDistritoNacimiento(Integer idDistritoNacimiento) {
			this.idDistritoNacimiento = idDistritoNacimiento;
		}

		public Integer getIdCentroPobladoProcedencia() {
			return idCentroPobladoProcedencia;
		}

		public void setIdCentroPobladoProcedencia(Integer idCentroPobladoProcedencia) {
			this.idCentroPobladoProcedencia = idCentroPobladoProcedencia;
		}

		public Integer getIdCentroPobladoDomicilio() {
			return idCentroPobladoDomicilio;
		}

		public void setIdCentroPobladoDomicilio(Integer idCentroPobladoDomicilio) {
			this.idCentroPobladoDomicilio = idCentroPobladoDomicilio;
		}

		public Integer getIdCentroPobladoNacimiento() {
			return idCentroPobladoNacimiento;
		}

		public void setIdCentroPobladoNacimiento(Integer idCentroPobladoNacimiento) {
			this.idCentroPobladoNacimiento = idCentroPobladoNacimiento;
		}

		public Integer getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}

}
