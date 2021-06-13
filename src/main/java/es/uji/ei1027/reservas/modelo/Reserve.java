package es.uji.ei1027.reservas.modelo;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Reserve {
	private int numberOfReserve;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfReservation;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfTheReserve;
	private String status;
	private int numberOfPeople;
	private String qrCode;
	private String dni;
	private int timeID;
	private String area;
	private TimeSlot time;
	
	
	
	public TimeSlot getTime() {
		return time;
	}
	public void setTime(TimeSlot time) {
		this.time = time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getNumberOfReserve() {
		return numberOfReserve;
	}
	public void setNumberOfReserve(int numberOfReserve) {
		this.numberOfReserve = numberOfReserve;
	}
	public LocalDate getDateOfReservation() {
		return dateOfReservation;
	}
	public void setDateOfReservation(LocalDate dateOfReserve) {
		this.dateOfReservation = dateOfReserve;
	}
	public LocalDate getDateOfTheReserve() {
		return dateOfTheReserve;
	}
	public void setDateOfTheReserve(LocalDate dateOfTheReserve) {
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
