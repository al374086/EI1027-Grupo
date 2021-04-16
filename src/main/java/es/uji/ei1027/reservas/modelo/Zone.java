package es.uji.ei1027.reservas.modelo;

public class Zone {
	private int capacity;
	private String letterAndNumber;
	private String nameArea;
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getLetterAndNumber() {
		return letterAndNumber;
	}
	public void setLetterAndNumber(String letterAndNumber) {
		this.letterAndNumber = letterAndNumber;
	}
	public String getNameArea() {
		return nameArea;
	}
	public void setNameArea(String nameArea) {
		this.nameArea = nameArea;
	}
	@Override
	public String toString() {
		return "zone [capacity=" + capacity + ", letterAndNumber=" + letterAndNumber + ", nameArea=" + nameArea + "]";
	}
	

}
