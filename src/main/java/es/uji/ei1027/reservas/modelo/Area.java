package es.uji.ei1027.reservas.modelo;

import java.awt.Image;

public class Area {
	
	private String name;
	//private boolean isRestricted;
	private String geographicalLocation;
	private String typeOfArea;
	private String physicalCharacteristics;
	private String description;
	private String lengthAndWidth;
	private String Orientation;
	private int codeMunicipality;
	private String imagen;
	
	private int isRestricted;
	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRestricted() {
		return isRestricted==1;
	}
	public void setRestricted(boolean isRestricted) {
		if (isRestricted)
			this.isRestricted = 1;
		else
			this.isRestricted = 0;
	}
	public String getGeographicalLocation() {
		return geographicalLocation;
	}
	public void setGeographicalLocation(String geographicalLocation) {
		this.geographicalLocation = geographicalLocation;
	}
	public String getTypeOfArea() {
		return typeOfArea;
	}
	public void setTypeOfArea(String typeOfArea) {
		this.typeOfArea = typeOfArea;
	}
	/*
	public Image getImagen() {
		return imagen;
	}
	public void setImagen(Image imagen) {
		this.imagen=imagen;
	}*/
	public String getPhysicalCharacteristics() {
		return physicalCharacteristics;
	}
	public void setPhysicalCharacteristics(String physicalCharacteristics) {
		this.physicalCharacteristics = physicalCharacteristics;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLengthAndWidth() {
		return lengthAndWidth;
	}
	public void setLengthAndWidth(String lenghtAndWidth) {
		this.lengthAndWidth = lenghtAndWidth;
	}
	public String getOrientation() {
		return Orientation;
	}
	public void setOrientation(String orientation) {
		Orientation = orientation;
	}
	public int getCodeMunicipality() {
		return codeMunicipality;
	}
	public void setCodeMunicipality(int codeMunicipality) {
		this.codeMunicipality = codeMunicipality;
	}
	
	@Override
	public String toString() {
		return "Area [name=" + name + ", isRestricted=" + isRestricted + ", geographicalLocation="
				+ geographicalLocation + ", typeOfArea=" + typeOfArea + ", physicalCharacteristics="
				+ physicalCharacteristics + ", description=" + description + ", lenghtAndWidth=" + lengthAndWidth
				+ ", Orientation=" + Orientation + ", codeMunicipality=" + codeMunicipality + "]";
	}
	
	
}
