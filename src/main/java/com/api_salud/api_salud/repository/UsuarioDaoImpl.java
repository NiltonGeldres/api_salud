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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.entity.UsuarioEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);

	@Override
	public String validarRegistroCompleto(UsuarioEntity usuario) {
	    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_security")
	            .withProcedureName("usuario_validar_registro_completo")
	            .declareParameters(
	                new SqlParameter("p_username", Types.VARCHAR),
	                new SqlParameter("p_email", Types.VARCHAR),
	                new SqlParameter("p_numero_documento", Types.VARCHAR),
	                new SqlParameter("p_apellido_paterno", Types.VARCHAR),
	                new SqlParameter("p_apellido_materno", Types.VARCHAR),
	                new SqlParameter("p_primer_nombre", Types.VARCHAR),
	                new SqlParameter("p_segundo_nombre", Types.VARCHAR),
	                new SqlOutParameter("p_resultado", Types.VARCHAR)
	            );

	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_username", usuario.getUsername())
	            .addValue("p_email", usuario.getEmail())
	            .addValue("p_numero_documento", usuario.getNumeroDocumento())
	            .addValue("p_apellido_paterno", usuario.getApellidoPaterno())
	            .addValue("p_apellido_materno", usuario.getApellidoMaterno())
	            .addValue("p_primer_nombre", usuario.getPrimerNombre())
	            .addValue("p_segundo_nombre", usuario.getSegundoNombre());

	    Map<String, Object> out = jdbcCall.execute(in);
	    return (String) out.get("p_resultado");
	}	
	
	
	//Testeado
	@Override  
	public UsuarioDto buscarPorIdUsuario(int idUsuario) {
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_security")
	            .withProcedureName("usuario_buscar_por_id_usuario")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_id_usuario", Types.INTEGER),
	                new SqlOutParameter("o_usuario", Types.REF_CURSOR) // O Types.OTHER según tu Postgres
	            )
	            .returningResultSet("o_usuario", BeanPropertyRowMapper.newInstance(UsuarioDto.class));

	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_id_usuario", idUsuario);

	    Map<String, Object> out = call.execute(in);

	    List<UsuarioDto> resultados = (List<UsuarioDto>) out.get("o_usuario");

	    return (resultados != null && !resultados.isEmpty()) ? resultados.get(0) : null;
	}
	
	// Testeado 
	@Override
	public int guardar(UsuarioEntity usuario) {
	        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withSchemaName("igm_security")
	                .withProcedureName("usuario_guardar")
	                .declareParameters(
	                    new SqlOutParameter("p_usuario_id_out", Types.INTEGER)
	                );
	        SqlParameterSource in = new MapSqlParameterSource()
	                .addValue("p_username", usuario.getUsername()) // "p_username" coincide con el SP
	                .addValue("p_password", usuario.getPassword())
	                .addValue("p_email", usuario.getEmail())
	                .addValue("p_apellido_paterno", usuario.getApellidoPaterno())
	                .addValue("p_apellido_materno", usuario.getApellidoMaterno())
	                .addValue("p_primer_nombre", usuario.getPrimerNombre())
	                .addValue("p_segundo_nombre", usuario.getSegundoNombre())
	                .addValue("p_numero_celular", usuario.getNumeroCelular())
	                .addValue("p_id_sexo", usuario.getIdSexo())
	                .addValue("p_id_tipo_documento", usuario.getIdTipoDocumento())
	                .addValue("p_numero_documento", usuario.getNumeroDocumento())
	                .addValue("p_fecha_alta", usuario.getFechaAlta())
	                .addValue("p_fecha_baja", usuario.getFechaBaja())
	                .addValue("p_fecha_modificacion", usuario.getFechaModificacion())
	                .addValue("p_estado", usuario.getEstado())
	                .addValue("p_identidad", usuario.getIdEntidad()); // Asegúrate que sea 'p_identidad' como en tu SP
	        Map<String, Object> out = jdbcCall.execute(in);
	        return (int) out.get("p_usuario_id_out"); // Nombre del parámetro OUT en tu SP
	}

	
	@Override
	public Usuario usuarioSave(Usuario request) {
		
		String username , password, email ;
		username = request.getUsername();
		password = encoder.encode(request.getPassword());
		email = request.getEmail();
		Usuario response = new Usuario();
		
        CallableStatement cstmt;
		try {
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
	
	@Override
	public Usuario usuarioLeer(int id_usuario) {
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
	public Usuario usuarioUsernameLeer(String username) {
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	    
	    // 1. Configuramos el SimpleJdbcCall una sola vez
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withProcedureName("usuario_username_leer")
	            .withSchemaName("igm_security")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_username", Types.VARCHAR),
	                new SqlOutParameter("o_usuario", Types.OTHER)
	            )
	            // Vinculamos el resultado directamente a tu clase Usuario
	            .returningResultSet("o_usuario", BeanPropertyRowMapper.newInstance(Usuario.class));

	    SqlParameterSource param = new MapSqlParameterSource()
	            .addValue("p_username", username);
	    
	    try {
	        Map<String, Object> out = call.execute(param);
	        
	        // 2. Extraemos la lista (Spring ya la convirtió a List<Usuario> por el returningResultSet)
	        List<Usuario> list = (List<Usuario>) out.get("o_usuario");
	        
	        if (list != null && !list.isEmpty()) {
	            return list.get(0); // Retornamos el primer usuario encontrado
	        }
	    } catch (Exception e) {
	        log.error("Error al leer usuario por username en Neon: ", e);
	    }
	    
	    return null; // Importante para que UserDetailsService lance UsernameNotFoundException
	}
	
/*	
	@Override
	public Usuario usuarioUsernameLeer(String  username) {
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

*/	
	
	@Override
	public Usuario usuarioActualizar(Usuario request) {
		
		String username , password, email ;
		username = request.getUsername();
		password = encoder.encode(request.getPassword());
		email = request.getEmail();
		Usuario response = new Usuario();
		
        CallableStatement cstmt;
		try {
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
		return response;
	}
}
