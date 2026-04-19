package com.api_salud.api_salud.mapper;

import org.springframework.stereotype.Component;

import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.request.PacienteRequest;
import com.api_salud.api_salud.request.UsuarioRequest;

@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public PacienteEntity requestToEntity(PacienteRequest request) {
        if (request == null) return null;

        PacienteEntity entity = new PacienteEntity();

        // Identificadores y Documentos
        entity.setIdDocIdentidad(request.getIdDocIdentidad());
        entity.setNroDocumento(request.getNroDocumento());
        entity.setIdEntidad(request.getIdEntidad());

        // Nombres (Convertimos a Mayúsculas por buena práctica en salud)
        entity.setApellidoPaterno(request.getApellidoPaterno().toUpperCase());
        entity.setApellidoMaterno(request.getApellidoMaterno().toUpperCase());
        entity.setPrimerNombre(request.getPrimerNombre().toUpperCase());
        entity.setSegundoNombre(request.getSegundoNombre() != null ? request.getSegundoNombre().toUpperCase() : null);
        entity.setTercerNombre(request.getTercerNombre() != null ? request.getTercerNombre().toUpperCase() : null);

        // Datos Personales
        entity.setFechaNacimiento(request.getFechaNacimiento()); // Formato YYYY-MM-DD
        entity.setIdTipoSexo(request.getIdTipoSexo());
        entity.setIdEstadoCivil(request.getIdEstadoCivil());
        entity.setIdIdioma(request.getIdIdioma());
        entity.setIdEtnia(request.getIdEtnia());
        entity.setIdReligion(request.getIdReligion());

        // Ubicación (Ubigeo 6 dígitos)
        entity.setIdUbigeoNacimiento(request.getIdUbigeoNacimiento());
        entity.setIdUbigeoDomicilio(request.getIdUbigeoDomicilio());
        entity.setIdUbigeoProcedencia(request.getIdUbigeoProcedencia());
        entity.setDireccionDomicilio(request.getDireccionDomicilio());

        // Contacto y Familia
        entity.setTelefono(request.getTelefono());
        entity.setEmail(request.getEmail());
        entity.setNombrePadre(request.getNombrePadre());
        entity.setNombreMadre(request.getNombreMadre());
        entity.setNroOrdenHijo(request.getNroOrdenHijo());
        entity.setIdGradoInstruccion(request.getIdGradoInstruccion());
        entity.setIdTipoOcupacion(request.getIdTipoOcupacion());

        // Salud y Otros
        entity.setGrupoSanguineo(request.getGrupoSanguineo());
        entity.setFactorRh(request.getFactorRh());
        entity.setObservacion(request.getObservacion());

        return entity;
    }

    @Override
    public PacienteEntity usuarioRequestToPacienteEntity(UsuarioRequest request) {
    	  if (request == null) return null;

    	    PacienteEntity entity = new PacienteEntity();
    	    
    	    // 1. Datos que SÍ vienen en el UsuarioRequest (SignUp)
    	    entity.setIdEntidad(request.getIdEntidad());
    	    entity.setIdDocIdentidad(request.getIdTipoDocumento());
    	    entity.setNroDocumento(request.getNumeroDocumento());
    	    entity.setApellidoPaterno(request.getApellidoPaterno());
    	    entity.setApellidoMaterno(request.getApellidoMaterno());
    	    entity.setPrimerNombre(request.getPrimerNombre());
    	    entity.setSegundoNombre(request.getSegundoNombre());
    	    entity.setTelefono(request.getNumeroCelular());
    	    entity.setEmail(request.getEmail());
    	    entity.setIdTipoSexo(request.getIdSexo());
    	    
    	    // 2. Campos obligatorios para el SP que nacen con valores por defecto o NULL
    	    entity.setNroHistoriaClinica(null); // Se genera tras el pago
    	    entity.setFechaNacimiento(null);  
    	    // El SignUp no lo pide, se completa en el perfil
    	    entity.setObservacion("Registro inicial desde App");
    	    entity.setIdUsuarioRegistro(0);     // O el ID del sistema si tienes uno
    	    
    	    // 3. Campos adicionales del Paciente (Inicializados en NULL para el SP)
    	    entity.setTercerNombre(null);
    	    entity.setIdEstadoCivil(null);
    	    entity.setIdIdioma(null);
    	    entity.setIdEtnia(null);
    	    entity.setIdReligion(null);
    	    entity.setGrupoSanguineo(null);
    	    entity.setFactorRh(null);
    	    
    	    // 4. Ubicaciones (Ubigeo)
    	    entity.setIdUbigeoNacimiento(null);
    	    entity.setIdUbigeoDomicilio(null);
    	    entity.setIdUbigeoProcedencia(null);
    	    entity.setDireccionDomicilio(null);
    	    
    	    // 5. Datos Familiares y Laborales
    	    entity.setNombrePadre(null);
    	    entity.setNombreMadre(null);
    	    entity.setNroOrdenHijo(null);
    	    entity.setIdGradoInstruccion(null);
    	    entity.setIdTipoOcupacion(null);

    	    return entity;
    }
    
}