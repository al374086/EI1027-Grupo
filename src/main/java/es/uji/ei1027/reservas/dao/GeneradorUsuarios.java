package es.uji.ei1027.reservas.dao;

import java.util.Collection;
import java.util.HashMap; 
import java.util.Map;
import org.jasypt.util.password.BasicPasswordEncryptor; 
import org.springframework.stereotype.Repository;
import es.uji.ei1027.reservas.modelo.Usuario;


@Repository
public class GeneradorUsuarios implements UserDao {
	  final Map<String, Usuario> knownUsers = new HashMap<String, Usuario>();

	  public GeneradorUsuarios() {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
		Usuario userAlice = new Usuario(); 
		userAlice.setUsername("alice"); 
		userAlice.setPassword(passwordEncryptor.encryptPassword("alice")); 
		knownUsers.put("alice", userAlice);
		  
		Usuario userBob = new Usuario(); 
	       userBob.setUsername("bob"); 
	       userBob.setPassword(passwordEncryptor.encryptPassword("bob")); 
	       knownUsers.put("bob", userBob);
	  }

	  @Override
	  public Usuario loadUserByUsername(String username, String password) { 
		  Usuario user = knownUsers.get(username.trim());
		  if (user == null)
			  return null; // Usuari no trobat
		  // Contrasenya
		 BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
		 if (passwordEncryptor.checkPassword(password, user.getPassword())) {
		 // Es deuria esborrar de manera segura el camp password abans de tornar-lo
			 return user; 
	        } 
		 else {
			 return null; // bad login!
		 }
	  }

	  @Override 
	  public Collection<Usuario> listAllUsers() {
		 return knownUsers.values();
	  }
	}