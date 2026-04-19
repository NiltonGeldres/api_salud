package com.api_salud.api_salud.entity;




public class User {

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


