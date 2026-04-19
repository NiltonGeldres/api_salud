package com.api_salud.api_salud.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.dto.PacienteCitadoDto;
import com.api_salud.api_salud.entity.CajaComprobantePagoEntity;
import com.api_salud.api_salud.entity.CitaEntity;
import com.api_salud.api_salud.entity.FacturacionCuentaAtencionEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioEntity;
import com.api_salud.api_salud.entity.FacturacionOrdenServicioPagoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioDespachoEntity;
import com.api_salud.api_salud.entity.FacturacionServicioPagoEntity;
import com.api_salud.api_salud.entity.PacienteEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.CajaComprobantePagoDao;
import com.api_salud.api_salud.repository.CitaDao;
import com.api_salud.api_salud.repository.FacturacionCuentaAtencionDao;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioDao;
import com.api_salud.api_salud.repository.FacturacionOrdenServicioPagoDao;
import com.api_salud.api_salud.repository.FacturacionServicioDespachoDao;
import com.api_salud.api_salud.repository.FacturacionServicioPagoDao;
import com.api_salud.api_salud.repository.PacienteDao;
import com.api_salud.api_salud.repository.UsuarioDao;

import com.api_salud.api_salud.request.CitaFacturacionRequest;
import com.api_salud.api_salud.request.CitaMedicoDiariaRequest;
import com.api_salud.api_salud.request.CitaPacientePendientesRequest;
import com.api_salud.api_salud.request.CitaRequest;
import com.api_salud.api_salud.request.FacturacionDetalleRequest;
import com.api_salud.api_salud.request.FacturacionRequest;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.PacienteCitadoResponse;

@Service
public class CitaServiceImpl  implements  CitaService{
	@Autowired
	private CitaDao citaDao;


	@Override
	public List<PacienteCitadoResponse> citaPacienteListarPendientes(CitaPacientePendientesRequest request) {
	    // Extraemos los datos del Request para el DAO
	    List<PacienteCitadoDto> dtos = citaDao.citaPacienteListarPendientes(
	        request.getIdPaciente(), 
	        request.getFecha()
	    );

	    // Transformación a Response...
	    return transformarDtosAResponses(dtos); 
	}

	@Override
	public List<PacienteCitadoResponse> citaMedicoListarDiaria(CitaMedicoDiariaRequest request) {
	    // Extraemos los datos del Request para el DAO
	    List<PacienteCitadoDto> dtos = citaDao.citaMedicoListarDiaria(
	        request.getIdMedico(), 
	        request.getFecha()
	    );

	    // Transformación a Response...
	    return transformarDtosAResponses(dtos); 
	}
	
	@Override
	public CitaResponse citaDisponible(CitaRequest request) {
		return citaDao.citasDisponiblesDia(
			request.getIdMedico(),
	        request.getFecha(),
	        request.getIdEspecialidad()
        );
	}

	@Override
	public int crearCita(CitaEntity c) { 
			//CitaResponse response = new CitaResponse(); 
		return citaDao.crearCita(c);
	}

	@Override
	public CitaResponse leerCita(int idCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int leerXIdProgramacionMedica(int idProgramacionMedica) {
		return citaDao.leerXIdProgramacionMedica(idProgramacionMedica);
	}

	
	   // ESTE ES EL MÉTODO QUE PREGUNTABAS
    private List<PacienteCitadoResponse> transformarDtosAResponses(List<PacienteCitadoDto> dtos) {
        List<PacienteCitadoResponse> listaFinal = new ArrayList<>();
        
        for (PacienteCitadoDto dto : dtos) {
            PacienteCitadoResponse res = new PacienteCitadoResponse();
            
            // Unimos datos para el Response (Independencia de capas)
            res.setIdCita(dto.getIdCita());
            res.setHoraInicio(dto.getHoraInicio());
            res.setNombres(dto.getNombres());
            res.setEspecialidad(dto.getEspecialidad());
            res.setFecha(dto.getFecha());
            
            // Lógica de negocio para la etiqueta
            String etiqueta = dto.getEstadoCita() ? "true" : "false";
            res.setAtendido(etiqueta);
            
            listaFinal.add(res);
        }
        
        return listaFinal;
    }
  

}

