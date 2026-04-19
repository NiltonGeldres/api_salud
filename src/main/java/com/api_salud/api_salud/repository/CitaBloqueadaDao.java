package com.api_salud.api_salud.repository;




import java.util.List;

import com.api_salud.api_salud.entity.CitaBloqueadaEntity;
import com.api_salud.api_salud.response.CitaBloqueadaResponse;


public interface CitaBloqueadaDao {
	
//	public CitaBloqueadaResponse cita(int idMedico, String fecha);

	public CitaBloqueadaResponse crearCitaBloqueada(
	 		    int idcitabloqueada,
			    int idusuario,
			    String fecha,
			    String horainicio ,
			    String horafin ,
			    String fechabloqueo ,
			    String horabloqueo ,
			    int idmedico,
			    String  tipousuario ,
			    String  usuario ,
			    int  idEntidad 			    
			    
			);

	public int eliminarCitaBloqueada(int idCitabloqueada);

	public int eliminarCitaBloqueadaXUsuario(int idUsuario);
	
	public List<CitaBloqueadaEntity> leerCitaBloqueada(int idMedico, String fecha) ;
	

}
