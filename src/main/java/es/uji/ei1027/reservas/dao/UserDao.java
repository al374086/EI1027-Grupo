package es.uji.ei1027.reservas.dao;

import java.util.Collection;
import es.uji.ei1027.reservas.modelo.Usuario;

public interface UserDao {
	Usuario loadUserByUsername(String username, String password);
 	Collection<Usuario> listAllUsers();
}
