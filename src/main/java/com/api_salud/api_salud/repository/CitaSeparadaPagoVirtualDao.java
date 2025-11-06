package com.api_salud.api_salud.repository;

import java.util.List;

import com.api_salud.api_salud.entity.CitaSeparadaPagadaEntity;
import com.api_salud.api_salud.response.CitaSeparadaPagoVirtualResponse;

public interface CitaSeparadaPagoVirtualDao {

	public CitaSeparadaPagoVirtualResponse crearCitaSeparadaPagoVirtual(
		    int idCitaSeparadaPagoVirtual ,
		    int idComprobantePago,
		    int idCitaSeparada ,
		    String fecha ,
		    String nroOperacion, 
		    String correo ,
		    String celular ,
		    Double monto ,
		    int idTipoOperacion ,
		    String origen ,
		    String destino ,
		    String entidadDestino ,
		    int idUsuario
		    
			) ;	
	public List<CitaSeparadaPagadaEntity> leerCitaSeparadaPagadaXMedico( int idMedicos) ;	

}
