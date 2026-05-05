package com.api_salud.api_salud.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
  // LLAVE_MUY_SECRETA => [Base64] => TExBVkVfTVVZX1NFQ1JFVEE=
//  private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";
  private static final String JWT_SECRET_KEY = "Hospital2021;;";

  
  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas

  
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(
		  UserDetails userDetails, 
		  int idRol, 
		  int idEntidad,
		  int idReferencia,
		  int idUsuario) {
      Map<String, Object> claims = new HashMap<>();
      
      // 1. Guardamos todos los roles, no solo el primero
      GrantedAuthority  rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
      claims.put("rol", rol);
      claims.put("idRol", idRol);
      claims.put("idEntidad", idEntidad);
      claims.put("idreferencia", idReferencia);
      claims.put("idUsuario", idUsuario);
      return createToken(claims, userDetails.getUsername());
  }

  /*
  public String generateToken(
		  UserDetails userDetails, 
		  String  usuarioNombres,
		  int idMedico, 
		  int idEntidad,
		  int idPaciente) {
      Map<String, Object> claims = new HashMap<>();
      
      // 1. Guardamos todos los roles, no solo el primero
      GrantedAuthority  rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
      claims.put("rol", rol);
      claims.put("usuarioNombres", usuarioNombres);
      claims.put("idMedico", idMedico);
      claims.put("idEntidad", idEntidad);
      claims.put("idPaciente", idPaciente);
      return createToken(claims, userDetails.getUsername());
  }
*/
  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
 
  /* agregado para el contexto */
  public Long extractIdEntidad(String token) {
	    Claims claims = extractAllClaims(token);
	    Object value = claims.get("idEntidad");
	    return value != null ? ((Number) value).longValue() : null;
	}

	public Long extractIdMedico(String token) {
	    Claims claims = extractAllClaims(token);
	    Object value = claims.get("idMedico");
	    return value != null ? ((Number) value).longValue() : null;
	}
  
  
    
}


