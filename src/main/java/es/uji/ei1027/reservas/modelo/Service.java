package es.uji.ei1027.reservas.modelo;

public class Service {
	
	private int idservice;
	private String name;
	private String type;
	private String description;
	
	

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

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	
	
	
	
	@Override
	public String toString() {
		return "Service {" + "idservice='" + idservice + "\'" + ", name='" + name + "\'" + ", description='" + description + "\'" + "}";
	}


}
