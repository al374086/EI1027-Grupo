package es.uji.ei1027.reservas.modelo;

public class Usuario {

	String username;
	String password;
	String rol;
	
	public String getUsername() {
		return username; 
	}
	
	public void setRol(String rol) {
		this.rol=rol;
	}
	
	public String getRol() {
		return rol;
	}

	public void setUsername(String username) {
	    this.username = username; 
	}

	public String getPassword() {
	   return password; 
	}

	public void setPassword(String password) {
	   this.password = password;
	}
}
