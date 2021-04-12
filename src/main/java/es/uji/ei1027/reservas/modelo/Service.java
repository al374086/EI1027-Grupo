package es.uji.ei1027.reservas.modelo;

public class Service {
	
	private int idservice;
	private String name;
	private String description;
	private int name_service;
	private String name_area;
	

	public Service() {
		
	}
	
	
	
	public int getIdservice() {
		return idservice;
	}


	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getName_service() {
		return name_service;
	}


	public void setName_service(int name_service) {
		this.name_service = name_service;
	}


	public String getName_area() {
		return name_area;
	}


	public void setName_area(String name_area) {
		this.name_area = name_area;
	}
	
	
	
	@Override
	public String toString() {
		return "Service {" + "idservice='" + idservice + "\'" + ", name='" + name + "\'" + ", description='" + description + "\'" + ",name_service='"
				+ name_service + "\'" + ", name_area='" + name_area + "\'" + "}";
	}


}
