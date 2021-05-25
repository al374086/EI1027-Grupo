package es.uji.ei1027.reservas.dao;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.reservas.modelo.Citizen;
import es.uji.ei1027.reservas.modelo.MunicipalManager;
import es.uji.ei1027.reservas.modelo.Staff;
import es.uji.ei1027.reservas.modelo.Usuario;

@Repository
public class LlamaUsuarios implements UserDao {
	
	@Autowired 
	CitizenDao usuarioDao;
	@Autowired 
	MunicipalManagerDao municipalManagerDao;
	

	public LlamaUsuarios() {

	}

	/*
	 * 
	 * public Citizen loadUSer(String username, String password) { Citizen user =
	 * new Citizen(); if(usuarioDao==null) return null;
	 * 
	 * String pass=Integer.toString(usuarioDao.getCitizenNombre(username).getPin());
	 * if(pass.equals(password)) { return user; } else { return null; // bad login!
	 * } }
	 * 
	 * Usuario userAlice = new Usuario(); userAlice.setUsername("alice");
	 * userAlice.setPassword(passwordEncryptor.encryptPassword("alice"));
	 * knownUsers.put("alice", userAlice);
	 * 
	 * Usuario userBob = new Usuario(); userBob.setUsername("bob");
	 * userBob.setPassword(passwordEncryptor.encryptPassword("bob"));
	 * knownUsers.put("bob", userBob); }
	 */

	@Override
	public Usuario loadUserByUsername(String dni, String password) {

		Usuario user = new Usuario();
		user.setUsername(dni);
		user.setPassword(password);
		
		
		
		//CitizenDao usuarioDao= new CitizenDao();
		Citizen ciudadano = new Citizen();
		ciudadano = usuarioDao.getCitizen(dni);

		if (ciudadano != null) {
			
			String numdni = ciudadano.getDni();
			String pass = Integer.toString(ciudadano.getPin());
			System.out.println("el num dni es:" + numdni);
			System.out.println("el num pass de dni es:" + pass);
			System.out.println("el password:" + password);
			
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			//if (user.getPassword().equals(pass)) {
			System.out.println("esfdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
			if (passwordEncryptor.checkPassword(password, pass)) {
				// Es deuria esborrar de manera segura el camp password abans de tornar-lo
				
				user.setRol("ciudadanos");
				
				return user;
				
			} else {
				
				return null; // bad login!
			}
		}
		
		
		
		/*
		
		StaffDao staffDao = new StaffDao();
		Staff staff = new Staff();
		staff= staffDao.getStaffNombre(username);

		if (staff != null) {

			String nombre = staff.getName();
			String pass = Integer.toString(staff.getPin());
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			if (passwordEncryptor.checkPassword(password, pass)) {
				// Es deuria esborrar de manera segura el camp password abans de tornar-lo
				return user;
				
			} else {
				return null; // bad login!
			}
		}
		
		*/
		
		
		
		
		//MunicipalManagerDao municipalManagerDao = new MunicipalManagerDao();
		MunicipalManager municipalmanager = new MunicipalManager();
		municipalmanager= municipalManagerDao.getMunicipalManager(dni);

		if (municipalManagerDao != null) {

			String numdni = municipalmanager.getDni();
			String pass = Integer.toString(municipalmanager.getCode());
			if (user.getPassword().equals(pass)) {
			//BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			//if (passwordEncryptor.checkPassword(password, pass)) {
			
				// Es deuria esborrar de manera segura el camp password abans de tornar-lo
				user.setRol("municipalManager");
				
				return user;
				
			} else {
				return null; // bad login!
			}
		}
		

		
		return null; // Usuari no trobat
		// Contrasenya
		

	}

	// @Override
	// public Collection<Usuario> listAllUsers() {
	// return knownUsers.values();
	// }
}
