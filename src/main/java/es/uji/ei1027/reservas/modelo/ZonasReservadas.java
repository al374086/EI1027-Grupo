package es.uji.ei1027.reservas.modelo;

public class ZonasReservadas {
	private int numberofreserve;
	private String letterAndNumber;
	private String nameArea;
	
	public int getNumberofreserve() {
		return numberofreserve;
	}
	public void setNumberofreserve(int numberofreserve) {
		this.numberofreserve = numberofreserve;
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
		return "ZonasReservadas [numberofreserve=" + numberofreserve + ", letterAndNumber=" + letterAndNumber
				+ ", namearea=" + nameArea + "]";
	}
	
	

}
