package es.uji.ei1027.reservas.modelo;

public class ZonasReservadas {
	private int numberofreserve;
	private String letterAndNumber;
	private String namearea;
	
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
	public String getNamearea() {
		return namearea;
	}
	public void setNamearea(String namearea) {
		this.namearea = namearea;
	}
	@Override
	public String toString() {
		return "ZonasReservadas [numberofreserve=" + numberofreserve + ", letterAndNumber=" + letterAndNumber
				+ ", namearea=" + namearea + "]";
	}
	
	

}
