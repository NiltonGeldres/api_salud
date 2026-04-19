package com.api_salud.api_salud.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.repository.CitaSeparadaPagoVirtualDao;
import com.api_salud.api_salud.request.CitaSeparadaPagoVirtualRequest;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;

@Service
public class CitaSeparadaPagoVirtualServiceImpl  implements CitaSeparadaPagoVirtualService
{
	@Autowired
	CitaSeparadaPagoVirtualDao citaSeparadaPagoVirtualDao; 
	
	@Override
	public CitaSeparadaPagoVirtualResponse crearCitaSeparadaPagoVirtual(CitaSeparadaPagoVirtualRequest request
			) {
		//String horaFin  = request.getHoraInicio();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm");
	    String fechaSeparada = dateFormat.format(new Date());
	    String horaSeparada = horaFormat.format(new Date());
	    
		CitaSeparadaPagoVirtualResponse response = null; 
		    response = new CitaSeparadaPagoVirtualResponse();
			response = citaSeparadaPagoVirtualDao.crearCitaSeparadaPagoVirtual(
					request.getIdCitaSeparadaPagoVirtual(),
					request.getIdComprobantePago(),
					request.getIdCitaSeparada(),
					request.getFecha(),
					request.getNroOperacion(), 
					request.getCorreo(),
					request.getCelular() ,
					request.getMonto() ,
					request.getIdTipoOperacion() ,
					request.getOrigen() ,
					request.getDestino() ,
					request.getEntidadDestino() ,
					request.getIdUsuario()
				);		
		
			
		return response;

	}

	@Override
	public List<CitaSeparadaPagadaEntity> leerCitaSeparadaPagadaXMedico(int idMedico) {

		List<CitaSeparadaPagadaEntity> response =	
				citaSeparadaPagoVirtualDao.leerCitaSeparadaPagadaXMedico(idMedico);
		return response;
	}

	
	
}
