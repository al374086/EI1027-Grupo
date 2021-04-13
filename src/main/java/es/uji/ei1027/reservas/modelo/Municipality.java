package es.uji.ei1027.reservas.modelo;

import java.util.Date;

public class Municipality {
	
	private int code;
	private String name;
	private String Country;

	public Municipality() {
		
	}

	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCountry() {
		return Country;
	}



	public void setCountry(String country) {
		Country = country;
	}



	@Override
	public String toString() {
		return "Municipality [code=" + code + ", name=" + name + ", Country=" + Country + "]";
	}

	
}
