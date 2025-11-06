package com.api_salud.api_salud.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.entity.CitaSeparadaEntity;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioResponse;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.CitaSeparadaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional

public class UsuarioDaoImpl  implements UsuarioDao{
	

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall  simpleJdbcCallUsuario;
	

	ResultSet rs;
	Connection connection;
	@Autowired
	private BCryptPasswordEncoder encoder ; 
	
	@Autowired
	private RoleDao roleDao ; 
		
	
	@Override
	public Usuario usuarioSave(Usuario request) {
		
		String username , password, email ;
		username = request.getUsername();
		System.out.println("DATO DE PASSWORD  " +request.getPassword());
		password = encoder.encode(request.getPassword());
		email = request.getEmail();
		Usuario response = new Usuario();
		
        CallableStatement cstmt;
		try {
		    System.out.println("** USUARIO SAVE CALL:  ");    
			connection= jdbcTemplate.getDataSource().getConnection();
			try {
	  			  cstmt = connection.prepareCall("{call igm_security.usuario_crear(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				  cstmt.setString(1, username);
				  cstmt.setString(2, password);
				  cstmt.setString(3, email);
				  cstmt.setString(4, request.getApellido_paterno());
				  cstmt.setString(5, request.getApellido_materno());
				  cstmt.setString(6, request.getPrimer_nombre());
				  cstmt.setString(7, request.getSegundo_nombre());
				  cstmt.setString(8, request.getNumero_celular());
				  cstmt.setString(9, request.getId_sexo());
				  cstmt.setString(10, request.getId_tipo_documento());
				  cstmt.setString(11, request.getNumero_documento());
				  cstmt.setString(12, request.getFecha_alta());
				  cstmt.setString(13, request.getFecha_baja());
				  cstmt.setString(14, request.getFecha_modificacion());
				  cstmt.setString(15, request.getEstado());
				  cstmt.registerOutParameter(16, Types.INTEGER);
				  cstmt.execute();
				  int idUsuario = cstmt.getInt(16);
//				  System.out.println("***USUARIO ES   "+idUsuario);
				  int idRol = roleDao.create(idUsuario);
				  if (idRol == 1 ) {	
				  	response = usuarioLeer(idUsuario );
				  } else {
					  	response = null;
				  }
   		          cstmt.close();
   		          connection.close();
   		          
			} catch (SQLException e) {
				e.printStackTrace();
  		         response.setEstado("False");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
           response.setEstado("False");
		}
		return response;
	}
	
	/* Obtener  id usuario*/
	@Override
	public Usuario usuarioLeer(int id_usuario) {
    	System.out.println("****** id_lee:  "+id_usuario);		
		Usuario response = null;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallUsuario = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_security.usuario_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_id_usuario", Types.INTEGER),
							            		new SqlOutParameter("o_usuario", Types.OTHER) 
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		 .addValue("p_id_usuario",id_usuario);
	    
	    System.out.println("EJECUTADO ");
   		Map<String, Object> out =   simpleJdbcCallUsuario.execute(param);
 	    if (out != null)  {
			List<Object> list =  (List<Object>) out.get("o_usuario") ;
 			for (Object row : list) {
	 			Usuario usuario = new Usuario();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			usuario = objectMapper.convertValue(row, Usuario.class) ;
	 			response= usuario;
 			}	
     	}
		return response;
	}
	
	/* Obtener  id usuario*/
	@Override
	public int xusername_leer(String p_usuario) {
    	System.out.println("****** xusername_lee:  "+p_usuario);		
		int  response = 0;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallUsuario = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_security.usuario_xusername_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_usuario", Types.VARCHAR),
				            				    new SqlOutParameter("p_id_usuario", Types.INTEGER)				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_usuario", p_usuario);	    
   		Map<String, Object> out = simpleJdbcCallUsuario.execute(param);
        int  list = (int) out.get("p_id_usuario") ;
 	    if (out != null)  {
	   		int id = (int) out.get("p_id_usuario");		   		
	   		response = id;
     	}
		return response;
	}
	
	@Override
	public Usuario usuarioUsernameLeer(String  username) {
    	System.out.println("****** Username:  "+username);		
		Usuario response = null;
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	    simpleJdbcCallUsuario = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_security.usuario_username_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_username", Types.VARCHAR),
							            		new SqlOutParameter("o_usuario", Types.OTHER) 
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		 .addValue("p_username",username);
	    
	    System.out.println("EJECUTADO ");
   		Map<String, Object> out =   simpleJdbcCallUsuario.execute(param);
 	    if (out != null)  {
			List<Object> list =  (List<Object>) out.get("o_usuario") ;
 			for (Object row : list) {
	 			Usuario usuario = new Usuario();
	 			ObjectMapper objectMapper = new ObjectMapper() ;
	 			usuario = objectMapper.convertValue(row, Usuario.class) ;
	 			response= usuario;
 			}	
     	}
		return response;
	}
	
	
	@Override
	public Usuario usuarioActualizar(Usuario request) {
		
		String username , password, email ;
		username = request.getUsername();
		password = encoder.encode(request.getPassword());
		email = request.getEmail();
		Usuario response = new Usuario();
	    System.out.println("Datos SEXO  "+request.getId_sexo());    
		
        CallableStatement cstmt;
		try {
		    System.out.println("* INICIANDO ACTUALIZACION ");    
			connection= jdbcTemplate.getDataSource().getConnection();
			try {
	  			  cstmt = connection.prepareCall("{call igm_security.usuario_actualizar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				  cstmt.setString(1, username);
				  cstmt.setString(2, password);
				  cstmt.setString(3, email);
				  cstmt.setString(4, request.getApellido_paterno());
				  cstmt.setString(5, request.getApellido_materno());
				  cstmt.setString(6, request.getPrimer_nombre());
				  cstmt.setString(7, request.getSegundo_nombre());
				  cstmt.setString(8, request.getNumero_celular());
				  cstmt.setString(9, request.getId_sexo());
				  cstmt.setString(10, request.getId_tipo_documento());
				  cstmt.setString(11, request.getNumero_documento());
				  cstmt.setString(12, request.getFecha_alta());
				  cstmt.setString(13, request.getFecha_baja());
				  cstmt.setString(14, request.getFecha_modificacion());
				  cstmt.setString(15, request.getEstado());
				  cstmt.registerOutParameter(16, Types.INTEGER);
				  cstmt.execute();
				  
 				  int idUsuario = cstmt.getInt(16);
				    System.out.println("* ID USUARIO "+idUsuario);    
				  if (idUsuario != 0 ) {	
				  	response = usuarioLeer(idUsuario );
				  } else {
				  	response = null;
				  }
   		          cstmt.close();
   		          connection.close();
  		          
			} catch (SQLException e) {
				e.printStackTrace();
  		         response.setEstado("False");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
           response.setEstado("False");
		}
	    System.out.println("* FINALIZADO ACTUALIZACION ");    
		return response;
	}
}
