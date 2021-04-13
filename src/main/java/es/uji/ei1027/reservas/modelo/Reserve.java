package es.uji.ei1027.reservas.modelo;

import java.sql.Date;

public class Reserve {
	private int numberOfReserve;
	private Date dateOfReservation;
	private Date dateOfTheReserve;
	private String status;
	private int numberOfPeople;
	private String qrCode;
	private String dni;
	private int timeID;
	public int getNumberOfReserve() {
		return numberOfReserve;
	}
	public void setNumberOfReserve(int numberOfReserve) {
		this.numberOfReserve = numberOfReserve;
	}
	public Date getDateOfReservation() {
		return dateOfReservation;
	}
	public void setDateOfReservation(Date dateOfReserve) {
		this.dateOfReservation = dateOfReserve;
	}
	public Date getDateOfTheReserve() {
		return dateOfTheReserve;
	}
	public void setDateOfTheReserve(Date dateOfTheReserve) {
		this.dateOfTheReserve = dateOfTheReserve;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getTimeID() {
		return timeID;
	}
	public void setTimeID(int timeID) {
		this.timeID = timeID;
	}
	@Override
	public String toString() {
		return "Reserve [numberOfReserve=" + numberOfReserve + ", dateOfReservation=" + dateOfReservation
				+ ", dateOfTheReserve=" + dateOfTheReserve + ", status=" + status + ", numberOfPeople=" + numberOfPeople
				+ ", qrCode=" + qrCode + ", dni=" + dni + ", timeID=" + timeID + "]";
	}
	
}
