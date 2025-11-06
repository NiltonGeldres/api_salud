package com.api_salud.api_salud.repository;

import java.util.Date;

import com.api_salud.api_salud.entity.PacienteEntity;


public interface PacienteDao {
	
	public int  crear(
		     String nroHistoriaClinica,
		     String nroDocumento,
		     String apellidoPaterno,
		     String apellidoMaterno,
		     String primerNombre,
		     String segundoNombre,
		     String tercerNombre,
		     String fechaNacimiento,
		     String telefono,
		     String email,
		     String direccionDomicilio,
		     String nombrePadre,
		     String nombreMadre,
		     String observacion,
		     String grupoSanguineo,
		     String factorRh,
		     Integer idTipoSexo,
		     Integer idGradoInstruccion,
		     Integer idEstadoCivil,
		     Integer idDocIdentidad,
		     Integer idTipoOcupacion,
		     Integer nroOrdenHijo,
		     Integer idIdioma,
		     Integer idEtnia,
		     Integer idReligion,
		     Integer idProcedencia,
		     Integer idPaisDomicilio,
		     Integer idPaisProcedencia,
		     Integer idPaisNacimiento,
		     Integer idDepartamentoProcedencia,
		     Integer idDepartamentoDomicilio,
		     Integer idDepartamentoNacimiento,
		     Integer idDistritoProcedencia,
		     Integer idDistritoDomicilio,
		     Integer idDistritoNacimiento,
		     Integer idCentroPobladoProcedencia,
		     Integer idCentroPobladoDomicilio,
		     Integer idCentroPobladoNacimiento,
		     Integer idUsuario
			) ;
	public PacienteEntity leerNroDocumento(PacienteEntity request) ;
	public PacienteEntity leerNombres(String nombres) ;
	public PacienteEntity actualizar(PacienteEntity request) ;
	public PacienteEntity leerIdUsuario(int idUsuario) ;
}
