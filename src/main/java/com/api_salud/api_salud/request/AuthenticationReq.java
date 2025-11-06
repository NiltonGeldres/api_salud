package com.api_salud.api_salud.request;

import java.io.Serializable;

public class AuthenticationReq implements Serializable {

  private static final long serialVersionUID = 1L;

  private String user;
  private String password;
  private String email;
  
  
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
  

  
  
}
