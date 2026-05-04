package com.api_salud.api_salud.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.api_salud.api_salud.dto.ProgramacionMedicaCabeceraDTO;
import com.api_salud.api_salud.entity.ProgramacionMedicaCabeceraEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class ProgramacionMedicaCabeceraDaoImpl implements ProgramacionMedicaCabeceraDao {

    private final JdbcTemplate jdbcTemplate;
    
    // Declaramos las llamadas como miembros de clase para reutilizarlas
    private SimpleJdbcCall leerMesCall;
    private SimpleJdbcCall crearCall;
    private SimpleJdbcCall actualizarCall;
    private SimpleJdbcCall eliminarCall;

    @Autowired
    public ProgramacionMedicaCabeceraDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.setResultsMapCaseInsensitive(true); //
        inicializarLlamadas();
    }

    private void inicializarLlamadas() {
        // 1. Configuración para Lectura (X Medico Mes)
        this.leerMesCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_programacion.programacion_medica_cabecera_xmedico_xmes_leer")
                .withoutProcedureColumnMetaDataAccess() //
                .returningResultSet("cur", BeanPropertyRowMapper.newInstance(ProgramacionMedicaCabeceraDTO.class))
                .declareParameters(
                    new SqlParameter("p_mes", Types.INTEGER),
                    new SqlParameter("p_anio", Types.INTEGER),
                    new SqlParameter("p_idmedico", Types.INTEGER),
                    new SqlParameter("p_idespecialidad", Types.INTEGER)
                );

        // 2. Configuración para Creación
        this.crearCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_programacion.programacion_medica_cabecera_crear")
                .withoutProcedureColumnMetaDataAccess() //
                .declareParameters(
                    new SqlParameter("p_idespecialidad", Types.INTEGER),
                    new SqlParameter("p_idservicio", Types.INTEGER),
                    new SqlParameter("p_idturno", Types.INTEGER),
                    new SqlParameter("p_idmedico", Types.INTEGER),
                    new SqlParameter("p_fecha", Types.VARCHAR),
                    new SqlParameter("p_fechareg", Types.VARCHAR),
                    new SqlParameter("p_idusuario", Types.INTEGER),
                    new SqlOutParameter("o_idprogramacioncabecera", Types.INTEGER)
                );

        // 3. Configuración para Actualización
        this.actualizarCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_programacion.programacion_medica_cabecera_actualizar")
                .withoutProcedureColumnMetaDataAccess() //
                .declareParameters(
                    new SqlParameter("p_idprogramacionmedica_cabecera", Types.INTEGER),
                    new SqlParameter("p_idespecialidad", Types.INTEGER),
                    new SqlParameter("p_idservicio", Types.INTEGER),
                    new SqlParameter("p_idturno", Types.INTEGER),
                    new SqlParameter("p_idmedico", Types.INTEGER),
                    new SqlParameter("p_fecha", Types.VARCHAR),
                    new SqlParameter("p_fechareg", Types.VARCHAR),
                    new SqlParameter("p_idusuario", Types.INTEGER)
                );

        // 4. Configuración para Eliminación
        this.eliminarCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("igm_programacion.programacion_medica_cabecera_eliminar")
                .withoutProcedureColumnMetaDataAccess() //
                .declareParameters(
                    new SqlParameter("p_idprogramacionmedica_cabecera", Types.INTEGER)
                );

        // Compilamos todas las llamadas al arrancar para ahorrar tiempo en ejecución
        this.leerMesCall.compile();
        this.crearCall.compile();
        this.actualizarCall.compile();
        this.eliminarCall.compile();
    }

    @Override
    public List<ProgramacionMedicaCabeceraDTO> xMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("p_mes", mes)
                .addValue("p_anio", ano)
                .addValue("p_idmedico", idMedico)
                .addValue("p_idespecialidad", idEspecialidad);

        Map<String, Object> out = leerMesCall.execute(param);
        return (List<ProgramacionMedicaCabeceraDTO>) out.get("cur");
    }

    @Override
    public int crear(ProgramacionMedicaCabeceraEntity programacion) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("p_idespecialidad", programacion.getIdEspecialidad())
                .addValue("p_idservicio", programacion.getIdServicio())
                .addValue("p_idturno", programacion.getIdTurno())
                .addValue("p_idmedico", programacion.getIdMedico())
                .addValue("p_fecha", programacion.getFecha())
                .addValue("p_fechareg", programacion.getFechaReg())
                .addValue("p_idusuario", programacion.getIdUsuario());

        Map<String, Object> out = crearCall.execute(param);
        return (int) out.get("o_idprogramacioncabecera");
    }

    @Override
    public int actualizar(ProgramacionMedicaCabeceraEntity programacion) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("p_idprogramacionmedica_cabecera", programacion.getIdProgramacionMedicaCabecera())
                .addValue("p_idespecialidad", programacion.getIdEspecialidad())
                .addValue("p_idservicio", programacion.getIdServicio())
                .addValue("p_idturno", programacion.getIdTurno())
                .addValue("p_idmedico", programacion.getIdMedico())
                .addValue("p_fecha", programacion.getFecha())
                .addValue("p_fechareg", programacion.getFechaReg())
                .addValue("p_idusuario", programacion.getIdUsuario());

        actualizarCall.execute(param);
        return 1; // Retornamos 1 indicando éxito en la operación
    }

    @Override
    public void eliminarXIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("p_idprogramacionmedica_cabecera", idProgramacionMedicaCabecera);
        
        eliminarCall.execute(param);
    }
}

/*
@Repository
@Transactional
public class ProgramacionMedicaCabeceraDaoImpl implements ProgramacionMedicaCabeceraDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProgCab;
	
	@Override
	public int crear(ProgramacionMedicaCabeceraEntity programacion) {
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_crear")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					 new SqlParameter("p_idespecialidad",      	Types.INTEGER)
				            					,new SqlParameter("p_idservicio",   		Types.INTEGER)
				            					,new SqlParameter("p_idturno",     			Types.INTEGER)
				            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
				            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
				            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
				            					,new SqlParameter("p_idusuario",			Types.INTEGER)
				            					,new SqlOutParameter("o_idprogramacioncabecera",    Types.INTEGER)				            					
				            					);
		
		
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_idusuario", programacion.getIdUsuario());
	    
			Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
		  	int idProgramacionCabecera= (int) out.get("o_idprogramacioncabecera");
		    if (idProgramacionCabecera != 0) {
		    	response= idProgramacionCabecera;
		    }	
		return response;		
	}
	

	@Override
	public List<ProgramacionMedicaCabeceraDTO> xMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	    
	    // 1. Configuramos el SimpleJdbcCall con un RowMapper
	    simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
	            .withProcedureName("igm_programacion.programacion_medica_cabecera_xmedico_xmes_leer")
	            .withoutProcedureColumnMetaDataAccess()
	            .returningResultSet("cur", BeanPropertyRowMapper.newInstance(ProgramacionMedicaCabeceraDTO.class)) // Mapeo automático rápido
	            .declareParameters(
	                new SqlParameter("p_mes", Types.INTEGER),
	                new SqlParameter("p_anio", Types.INTEGER),
	                new SqlParameter("p_idmedico", Types.INTEGER),
	                new SqlParameter("p_idespecialidad", Types.INTEGER)
	            );

	    SqlParameterSource param = new MapSqlParameterSource()
	            .addValue("p_mes", mes)
	            .addValue("p_anio", ano)
	            .addValue("p_idmedico", idMedico)
	            .addValue("p_idespecialidad", idEspecialidad);

	    // 2. Ejecutamos y obtenemos la lista ya mapeada
	    Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
	    
	    return (List<ProgramacionMedicaCabeceraDTO>) out.get("cur");
	}
	

	@Override
	public int actualizar(ProgramacionMedicaCabeceraEntity programacion) {
		int response=0 ;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_actualizar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
	            					 new SqlParameter("p_idprogramacionmedica_cabecera",      	Types.INTEGER)
	            					,new SqlParameter("p_idespecialidad",      	Types.INTEGER)
	            					,new SqlParameter("p_idservicio",   		Types.INTEGER)
	            					,new SqlParameter("p_idturno",     			Types.INTEGER)
	            					,new SqlParameter("p_idmedico", 			Types.INTEGER)
	            					,new SqlParameter("p_fecha", 				Types.VARCHAR)
	            					,new SqlParameter("p_fechareg", 			Types.VARCHAR)
	            					,new SqlParameter("p_idusuario",			Types.INTEGER)
            					);
		
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_idprogramacionmedica_cabecera", programacion.getIdProgramacionMedicaCabecera())	    
	    		.addValue("p_idespecialidad", programacion.getIdEspecialidad())	    
	    		.addValue("p_idservicio", programacion.getIdServicio())	    
	    		.addValue("p_idturno", programacion.getIdTurno())	    
	    		.addValue("p_idmedico", programacion.getIdMedico())	    
	    		.addValue("p_fecha", programacion.getFecha())	    
	    		.addValue("p_fechareg", programacion.getFechaReg())	    
	    		.addValue("p_idusuario", programacion.getIdUsuario());
	    
			Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
		return response;		

	}

	public void eliminarXIdProgramacionMedicaCabecera(int idProgramacionMedicaCabecera) {
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_programacion.programacion_medica_cabecera_eliminar")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( new SqlParameter("p_idprogramacionmedica_cabecera", Types.INTEGER));
	    SqlParameterSource param = new MapSqlParameterSource().addValue("p_idprogramacionmedica_cabecera",idProgramacionMedicaCabecera);
   		Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
	}
}

*/

/*	
@Override
public List<ProgramacionMedicaCabeceraDTO> xMedicoMesLeer(int mes, int ano, int idMedico, int idEspecialidad) {
	List<ProgramacionMedicaCabeceraDTO> response = null;
    List<ProgramacionMedicaCabeceraDTO> res =new ArrayList<>();
	jdbcTemplate.setResultsMapCaseInsensitive(true);
	simpleJdbcCallProgCab = new SimpleJdbcCall(jdbcTemplate)
			    		.withProcedureName("igm_programacion.programacion_medica_cabecera_xmedico_xmes_leer")
			            .withoutProcedureColumnMetaDataAccess()
			            .declareParameters( 
			            					 new SqlParameter("p_mes",      Types.INTEGER)
			            					,new SqlParameter("p_anio",     Types.INTEGER)
			            					,new SqlParameter("p_idmedico", Types.INTEGER)
			            					,new SqlParameter("p_idespecialidad", Types.INTEGER)
			            					,new SqlOutParameter("cur",     Types.OTHER )				            					
			            					);
		
    SqlParameterSource param = new MapSqlParameterSource()
    		.addValue("p_mes", mes)	    
    		.addValue("p_anio", ano)	    
			.addValue("p_idmedico", idMedico)    
			.addValue("p_idespecialidad", idEspecialidad);    
		Map<String, Object> out = simpleJdbcCallProgCab.execute(param);
	List<Object>  list =(List<Object>) out.get("cur") ;
	    if (!list.isEmpty()) {
			for (Object row : list) {
				ProgramacionMedicaCabeceraDTO cita = new ProgramacionMedicaCabeceraDTO();
 			ObjectMapper objectMapper = new ObjectMapper() ;
 			cita = objectMapper.convertValue(row, ProgramacionMedicaCabeceraDTO.class) ;
 			res.add(cita);
			}
			response =res;
	    } else	{
	    	response = null;
 	}		
	return response;
}
*/