package es.uji.ei1027.reservas.modelo;

public class Citizen {
	private String dni;
	private String name;
	private String address;
	private String town;
	private String country;
	private int cp;
	private String pin;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	@Override
	public String toString() {
		return "Citizen [dni=" + dni + ", name=" + name + ", address=" + address + ", town=" + town + ", country="
				+ country + ", cp=" + cp + ", pin=" + pin + "]";
	}
}
