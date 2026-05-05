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
import com.api_salud.api_salud.response.UsuarioContextoResponse;

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
	    authManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
	    //UsuarioJwtResponse  usuario = usuarioDao.usuarioUsernameLeer(user);
	    UsuarioContextoResponse  usuario = usuarioDao.usuarioObtenerDatosContextoPorUsername(user);
	    
	    if (usuario == null) {
	        throw new RuntimeException("Usuario no encontrado");
	    }
	    
	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(user);
	    String jwt = jwtUtilService.generateToken(
	        userDetails, 
	        usuario.getIdRol(),   
	        usuario.getIdEntidad(), 
	        usuario.getIdReferencia(),
	        usuario.getIdUsuario()
	    );

	    return new TokenInfo(jwt);
	}
	

}
