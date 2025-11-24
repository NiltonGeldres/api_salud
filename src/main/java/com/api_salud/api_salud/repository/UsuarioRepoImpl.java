package com.api_salud.api_salud.repository;

import com.api_salud.api_salud.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;

// Esta anotación es CRÍTICA. Le dice a Spring que cree una instancia de esta clase
// para inyectarla donde se pida la interfaz UsuarioRepo.
@Repository
public class UsuarioRepoImpl implements UsuarioRepo {

private final SimpleJdbcCall simpleJdbcCall;

/**
 * Constructor que inicializa SimpleJdbcCall.
 * @param dataSource La fuente de datos autoconfigurada por Spring Boot (obtenida de application.properties).
 */
public UsuarioRepoImpl(DataSource dataSource) {
    
    // Configuración para la llamada a la función almacenada de PostgreSQL
    this.simpleJdbcCall = new SimpleJdbcCall(dataSource)
        .withSchemaName("igm_seguridad") // Asegúrate de que este sea el esquema correcto
        .withFunctionName("fn_usuarios_obtener_por_nombre") // Nombre de tu función en la BD
        .returningResultSet("cur_usuario", (ResultSet rs, int rowNum) -> {
            Usuario usuario = new Usuario();
            try {
                // Mapeo de columnas de la BD al POJO Usuario
                usuario.setId_usuario(rs.getInt("id_usuario")); 
                usuario.setUsername(rs.getString("username")); 
                usuario.setPassword(rs.getString("password")); 
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                usuario.setApellido_materno(rs.getString("apellido_materno"));
                usuario.setPrimer_nombre(rs.getString("primer_nombre"));
                usuario.setSegundo_nombre(rs.getString("segundo_nombre"));
                usuario.setNumero_celular(rs.getString("numero_celular"));
                usuario.setId_sexo(rs.getString("id_sexo"));
                usuario.setId_tipo_documento(rs.getString("id_tipo_documento"));
                usuario.setNumero_documento(rs.getString("numero_documento"));
                usuario.setFecha_alta(rs.getString("fecha_alta"));
                usuario.setFecha_baja(rs.getString("fecha_baja"));
                usuario.setFecha_modificacion(rs.getString("fecha_modificacion"));

                return usuario;
            } catch (SQLException e) {
                throw new RuntimeException("Error al mapear ResultSet a Usuario. Verifique nombres de columnas y el POJO: " + e.getMessage(), e);
            }
        })
        .declareParameters( 
            // Declaración del parámetro de entrada que la función almacenada espera (p_username)
            new org.springframework.jdbc.core.SqlParameter("p_username", Types.VARCHAR), 
            // Declaración del parámetro de salida (el cursor)
            new org.springframework.jdbc.core.SqlOutParameter("cur_usuario", Types.OTHER) 
        );
}

/**
 * Implementación de la búsqueda de un usuario por nombre de usuario.
 */
@Override
public Optional<Usuario> findByUsername(String username) {
    
    MapSqlParameterSource params = new MapSqlParameterSource();
    // Mapeamos el parámetro 'username' del método al parámetro de la función de BD
    params.addValue("p_username", username);

    try {
        // Ejecuta la llamada a la función almacenada
        Map<String, Object> out = simpleJdbcCall.execute(params);

        // El resultado mapeado (la lista de Usuarios) se recupera del cursor
        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = (List<Usuario>) out.get("cur_usuario");

        if (usuarios != null && !usuarios.isEmpty()) {
            return Optional.of(usuarios.get(0));
        }

        return Optional.empty();

    } catch (Exception e) {
        System.err.println("Error grave al buscar usuario por JDBC: " + e.getMessage());
        e.printStackTrace();
        return Optional.empty();
    }
}
}