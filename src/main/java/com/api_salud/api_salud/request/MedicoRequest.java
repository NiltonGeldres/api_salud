package com.api_salud.api_salud.request;

import java.time.LocalDate;

public class MedicoRequest {

    // --- Datos de Contexto ---
    private int idEntidad;
    private int idEspecialidad; // Útil si se asigna especialidad inicial al registrar
    private String usuario;     // Usuario que realiza la acción (auditoría)

    // --- Datos de Seguridad / Usuario (igm_security) ---
    private String passwordTemporal;
    private String email;
    private String numeroCelular;
    private String idSexo;          // Ej: "1" o "M"
    private String idTipoDocumento; // Ej: "1" (DNI)

    // --- Datos Filiatorios / Empleado (igm_maestros.empleados) ---
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private LocalDate fechaNacimiento;
    private int idCondicionTrabajo;
    private int idTipoEmpleado;
    private int idArea;
    private String codigoPlanilla;
    private String numeroCuenta;
    private int idBanco;

    // --- Datos Asistenciales / Médico (igm_maestros.medicos) ---
    private String colegiatura;         // CMP
    private int idTipoFinanciamiento;
    
    private String usuarioSistema;         // Usuario Sistema
    private int idMedico;
    
    

    // ================= Getters y Setters =================

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswordTemporal() {
        return passwordTemporal;
    }

    public void setPasswordTemporal(String passwordTemporal) {
        this.passwordTemporal = passwordTemporal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(String idSexo) {
        this.idSexo = idSexo;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCondicionTrabajo() {
        return idCondicionTrabajo;
    }

    public void setIdCondicionTrabajo(int idCondicionTrabajo) {
        this.idCondicionTrabajo = idCondicionTrabajo;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getCodigoPlanilla() {
        return codigoPlanilla;
    }

    public void setCodigoPlanilla(String codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

    public int getIdTipoFinanciamiento() {
        return idTipoFinanciamiento;
    }

    public void setIdTipoFinanciamiento(int idTipoFinanciamiento) {
        this.idTipoFinanciamiento = idTipoFinanciamiento;
    }

	public String getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(String usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
    
    
}