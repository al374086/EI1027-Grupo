package es.uji.ei1027.reservas.modelo;

import java.util.Date;

public class Municipality {
	
	private int code;
	private String name;
	private String province;

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



	public String getProvince() {
		return province;
	}



	public void setProvince(String province) {
		this.province = province;
	}



	@Override
	public String toString() {
		return "Municipality [code=" + code + ", name=" + name + ", Province=" + province + "]";
	}

	
}
