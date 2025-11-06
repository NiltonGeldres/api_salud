package com.api_salud.api_salud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		int id;
		String user	;
		String password;
		int idJwt;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
		public int getIdJwt() {
			return idJwt;
		}
		public void setIdJwt(int idJwt) {
			this.idJwt = idJwt;
		}
}


