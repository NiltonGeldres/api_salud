/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api_salud.api_salud.entity;

import java.util.ArrayList;

/**
 *
 * @author ngeldres
 */
public class AtencionMedicaEntity {

    int     idAtencionMedica;
    int     idAtencion ;
    int     idCuentaAtencion;
    int     idPaciente ;
    String  nombresPaciente;
    int     idMedico;
    String  nombresMedico;
    String  colegiaturaMedico;
    String  tipoServicioMedico;
    String  rneMedico;
    String  documento;
    String  historiaClinica;
    int     idServicio;
    String  descripcionServicio;
    int     idCamaIngreso;
    String  fechaRegistro;
    String  horaRegistro;
    String  fechaCita;
    String  horaCita;
    String  fechaIngreso;
    String  horaIngreso;
    String  origenRegistroUsuario;
    int     idEstadoAtencion;
    int     idUsuario;
    String  mensajeEstadoAtencion;
    String  fuenteFinanciamiento;
    String rubrica ;

    ArrayList<AtencionMedicaTriajeEntity>               detalleTriajes ;
    ArrayList<AtencionMedicaSintomaEntity>              detalleSintomas ;
    ArrayList<AtencionMedicaAntecedenteEntity>          detalleAntecedentes ;
    ArrayList<AtencionMedicaExamenFisicoEntity>         detalleExamenFisicos ;
    ArrayList<AtencionMedicaDiagnosticoEntity>          detalleDiagnosticos ;
    ArrayList<AtencionMedicaDiagnosticoLabEntity>       detalleDiagnosticosLab ;
    ArrayList<AtencionMedicaDiscapacidadEntity>         detalleDiscapacidad ;
    ArrayList<AtencionMedicaDiscapacidadOtrosEntity>    detalleDiscapacidadOtros ;
    ArrayList<AtencionMedicaTratamientoEntity>          detalleTratamientos ;
    ArrayList<AtencionMedicaMedicacionEntity>           detalleMedicacion ;    
    ArrayList<AtencionMedicaExamenesEntity>             detalleExamenes ;
    
    
	public int getIdAtencionMedica() {
		return idAtencionMedica;
	}
	public void setIdAtencionMedica(int idAtencionMedica) {
		this.idAtencionMedica = idAtencionMedica;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdCuentaAtencion() {
		return idCuentaAtencion;
	}
	public void setIdCuentaAtencion(int idCuentaAtencion) {
		this.idCuentaAtencion = idCuentaAtencion;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombresPaciente() {
		return nombresPaciente;
	}
	public void setNombresPaciente(String nombresPaciente) {
		this.nombresPaciente = nombresPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public String getNombresMedico() {
		return nombresMedico;
	}
	public void setNombresMedico(String nombresMedico) {
		this.nombresMedico = nombresMedico;
	}
	public String getColegiaturaMedico() {
		return colegiaturaMedico;
	}
	public void setColegiaturaMedico(String colegiaturaMedico) {
		this.colegiaturaMedico = colegiaturaMedico;
	}
	public String getTipoServicioMedico() {
		return tipoServicioMedico;
	}
	public void setTipoServicioMedico(String tipoServicioMedico) {
		this.tipoServicioMedico = tipoServicioMedico;
	}
	public String getRneMedico() {
		return rneMedico;
	}
	public void setRneMedico(String rneMedico) {
		this.rneMedico = rneMedico;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getHistoriaClinica() {
		return historiaClinica;
	}
	public void setHistoriaClinica(String historiaClinica) {
		this.historiaClinica = historiaClinica;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public String getDescripcionServicio() {
		return descripcionServicio;
	}
	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}
	public int getIdCamaIngreso() {
		return idCamaIngreso;
	}
	public void setIdCamaIngreso(int idCamaIngreso) {
		this.idCamaIngreso = idCamaIngreso;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getHoraRegistro() {
		return horaRegistro;
	}
	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	public String getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}
	public String getHoraCita() {
		return horaCita;
	}
	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public String getOrigenRegistroUsuario() {
		return origenRegistroUsuario;
	}
	public void setOrigenRegistroUsuario(String origenRegistroUsuario) {
		this.origenRegistroUsuario = origenRegistroUsuario;
	}
	public int getIdEstadoAtencion() {
		return idEstadoAtencion;
	}
	public void setIdEstadoAtencion(int idEstadoAtencion) {
		this.idEstadoAtencion = idEstadoAtencion;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMensajeEstadoAtencion() {
		return mensajeEstadoAtencion;
	}
	public void setMensajeEstadoAtencion(String mensajeEstadoAtencion) {
		this.mensajeEstadoAtencion = mensajeEstadoAtencion;
	}
	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}
	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}
	public String getRubrica() {
		return rubrica;
	}
	public void setRubrica(String rubrica) {
		this.rubrica = rubrica;
	}
	public ArrayList<AtencionMedicaTriajeEntity> getDetalleTriajes() {
		return detalleTriajes;
	}
	public void setDetalleTriajes(ArrayList<AtencionMedicaTriajeEntity> detalleTriajes) {
		this.detalleTriajes = detalleTriajes;
	}
	public ArrayList<AtencionMedicaSintomaEntity> getDetalleSintomas() {
		return detalleSintomas;
	}
	public void setDetalleSintomas(ArrayList<AtencionMedicaSintomaEntity> detalleSintomas) {
		this.detalleSintomas = detalleSintomas;
	}
	public ArrayList<AtencionMedicaAntecedenteEntity> getDetalleAntecedentes() {
		return detalleAntecedentes;
	}
	public void setDetalleAntecedentes(ArrayList<AtencionMedicaAntecedenteEntity> detalleAntecedentes) {
		this.detalleAntecedentes = detalleAntecedentes;
	}
	public ArrayList<AtencionMedicaExamenFisicoEntity> getDetalleExamenFisicos() {
		return detalleExamenFisicos;
	}
	public void setDetalleExamenFisicos(ArrayList<AtencionMedicaExamenFisicoEntity> detalleExamenFisicos) {
		this.detalleExamenFisicos = detalleExamenFisicos;
	}
	public ArrayList<AtencionMedicaDiagnosticoEntity> getDetalleDiagnosticos() {
		return detalleDiagnosticos;
	}
	public void setDetalleDiagnosticos(ArrayList<AtencionMedicaDiagnosticoEntity> detalleDiagnosticos) {
		this.detalleDiagnosticos = detalleDiagnosticos;
	}
	public ArrayList<AtencionMedicaDiagnosticoLabEntity> getDetalleDiagnosticosLab() {
		return detalleDiagnosticosLab;
	}
	public void setDetalleDiagnosticosLab(ArrayList<AtencionMedicaDiagnosticoLabEntity> detalleDiagnosticosLab) {
		this.detalleDiagnosticosLab = detalleDiagnosticosLab;
	}
	public ArrayList<AtencionMedicaDiscapacidadEntity> getDetalleDiscapacidad() {
		return detalleDiscapacidad;
	}
	public void setDetalleDiscapacidad(ArrayList<AtencionMedicaDiscapacidadEntity> detalleDiscapacidad) {
		this.detalleDiscapacidad = detalleDiscapacidad;
	}
	public ArrayList<AtencionMedicaDiscapacidadOtrosEntity> getDetalleDiscapacidadOtros() {
		return detalleDiscapacidadOtros;
	}
	public void setDetalleDiscapacidadOtros(ArrayList<AtencionMedicaDiscapacidadOtrosEntity> detalleDiscapacidadOtros) {
		this.detalleDiscapacidadOtros = detalleDiscapacidadOtros;
	}
	public ArrayList<AtencionMedicaTratamientoEntity> getDetalleTratamientos() {
		return detalleTratamientos;
	}
	public void setDetalleTratamientos(ArrayList<AtencionMedicaTratamientoEntity> detalleTratamientos) {
		this.detalleTratamientos = detalleTratamientos;
	}
	public ArrayList<AtencionMedicaMedicacionEntity> getDetalleMedicacion() {
		return detalleMedicacion;
	}
	public void setDetalleMedicacion(ArrayList<AtencionMedicaMedicacionEntity> detalleMedicacion) {
		this.detalleMedicacion = detalleMedicacion;
	}
	public ArrayList<AtencionMedicaExamenesEntity> getDetalleExamenes() {
		return detalleExamenes;
	}
	public void setDetalleExamenes(ArrayList<AtencionMedicaExamenesEntity> detalleExamenes) {
		this.detalleExamenes = detalleExamenes;
	}

    
    
   
}
