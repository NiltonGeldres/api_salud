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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import com.api_salud.api_salud.dto.RolDto;
import com.api_salud.api_salud.dto.UsuarioDto;
import com.api_salud.api_salud.entity.Role;
import com.api_salud.api_salud.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	private SimpleJdbcCall simpleJdbcCallRol;
	
	//private ResultSet rsRole;
	private Connection connection;

	@Override  
	public List<RolDto>  buscarPorIdUsuario(int idUsuario) {
	    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withSchemaName("igm_security")
	            .withProcedureName("roles_buscar_por_id_usuario")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                new SqlParameter("p_id_usuario", Types.INTEGER),
	                new SqlOutParameter("o_roles", Types.REF_CURSOR) // O Types.OTHER según tu Postgres
	            )
	            .returningResultSet("o_roles", BeanPropertyRowMapper.newInstance(RolDto.class));

	    SqlParameterSource in = new MapSqlParameterSource()
	            .addValue("p_id_usuario", idUsuario);

	    Map<String, Object> out = call.execute(in);

	    List<RolDto> resultados = (List<RolDto>) out.get("o_roles");
	    return (resultados != null) ? resultados : new ArrayList<>();    
	}	
	
	
	@Override
	public List<GrantedAuthority> findByUsername(String  username){
		List<GrantedAuthority> roles = new ArrayList<>();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		simpleJdbcCallRol = new SimpleJdbcCall(jdbcTemplate)
				    		.withProcedureName("igm_security.role_leer")
				            .withoutProcedureColumnMetaDataAccess()
				            .declareParameters( 
				            					new SqlParameter("p_username", Types.VARCHAR)
				            					,new SqlOutParameter("cur", Types.OTHER )				            					
				            					);
	    SqlParameterSource param = new MapSqlParameterSource()
	    		.addValue("p_username", username);	 
	    
			Map<String, Object> out = simpleJdbcCallRol.execute(param);

		    if (out != null) { 
		        List<Object>  list = (List<Object>) out.get("cur") ;
		    	   for (Object row : list) {
		         	  Role rol;
		    	   	  ObjectMapper objectMapper = new ObjectMapper() ;
		    		  rol = objectMapper.convertValue(row, Role.class) ;
						roles.add(new SimpleGrantedAuthority(rol.getName()));
		    	   }
		 	}
		    
		 	for (GrantedAuthority row : roles) {
			}	  
		    
		return roles;
		
	};

	@Override
	public  int create(int idUsuario){
		
		int idRol= 0;
        CallableStatement cstmt;
 		try {
 			connection= jdbcTemplate.getDataSource().getConnection();
	        try {
				cstmt = connection.prepareCall("{call igm_security.rol_crear(?,?)}");
	  		    cstmt.setInt(1,idUsuario);
  			    cstmt.registerOutParameter(2, Types.INTEGER);	  		    
				cstmt.execute();
	 		    idRol = cstmt.getInt(2);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return idRol;          
	};
}






