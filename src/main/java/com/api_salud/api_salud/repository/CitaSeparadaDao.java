
package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.response.CitaSeparadaEntityResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;

public interface CitaSeparadaDao {


	public int  crearCitaSeparada(
//			public CitaSeparadaResponse crearCitaSeparada(
			int idCitaSeparada,
		    String fecha ,
		    String horaInicio ,
		    String horaFin ,
		    int idPaciente ,
		    int idMedico ,
		    int idEspecialidad ,
		    int idServicio ,
		    int idProgramacion ,
		    int idProducto ,
		    String fechaSolicitud,
		    String horaSolicitud,
		    String tipoUsuario,
		    String fechaSeparacion,
		    double precioUnitario,
		    int idusuario
		    
			) ;	
	
	public CitaSeparadaResponse leerCitaSeparadaXIdpaciente(int idUsuario);	
	
	public CitaSeparadaEntityResponse leerCitaSeparadaXIdCitaSeparada(int  idCitaSeparada); 	
	
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualXIdpaciente(int idUsuario);
	
	public CitaSeparadaResponse leerCitaSeparadaConPagoVirtualConcomprobanteXIdpaciente(int idUsuario);	

	public void actualizaIdCita(int idcitaSeparada, int idCita);
	
}
