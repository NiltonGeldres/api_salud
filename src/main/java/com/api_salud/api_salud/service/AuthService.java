package com.api_salud.api_salud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import com.api_salud.api_salud.entity.Usuario;
import com.api_salud.api_salud.repository.EmpleadoDaoImpl;
import com.api_salud.api_salud.repository.MedicoDaoImpl;
import com.api_salud.api_salud.repository.UsuarioDao;
import com.api_salud.api_salud.response.TokenInfo;


@Service
public class AuthService {

	@Autowired
    private AuthenticationManager authManager; // Usa solo uno
	@Autowired
    private JwtUtilService jwtUtilService;
	@Autowired
    private UsuarioDao usuarioDao; 
	@Autowired
    private UserDetailsService usuarioDetailsService;
    
	@Autowired
	private MedicoDaoImpl medicoDaoImpl;
	@Autowired
	private EmpleadoDaoImpl empleadoDaoImpl;

	
    public TokenInfo login(String user, String password) {
        // 1. Autenticar (Si falla, Spring lanza una excepción solo)
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));

        // 2. Buscar al usuario en la DB para obtener datos reales
        Usuario usuario = usuarioDao.usuarioUsernameLeer(user);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado en la base de datos");
        }

        // 3. Preparar datos para el Token
        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(user);
        
        // Aquí deberías mapear los IDs reales de tu entidad usuario/médico
        // Ejemplo: int idMedico = usuario.getMedico().getId();
		int idMedico =  medicoDaoImpl.medicoXUsuarioLeer(user);
		int idEntidad =usuario.getId_entidad();
		
		
        String usuarioNombres = String.format("%s %s %s %s", 
            usuario.getApellido_paterno(), 
            usuario.getApellido_materno(), 
            usuario.getPrimer_nombre(), 
            usuario.getSegundo_nombre()).trim();
        int idPaciente = usuario.getIdPaciente();
        // 4. Generar el JWT con los nuevos Claims
        String jwt = jwtUtilService.generateToken(userDetails, usuarioNombres, idMedico,idEntidad,idPaciente);

        // Asegúrate que este TokenInfo sea el del paquete .response
        return new TokenInfo(jwt);
    }
}

/*
private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
        .compact();
  }*/