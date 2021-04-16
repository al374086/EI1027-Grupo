package es.uji.ei1027.reservas.modelo;

import java.util.Date;

public class Workplan {
    public Workplan(){
    }

    private Date initialDate;
    private Date endDate;
    private String nameArea;
    private String dni;

    @Override
    public String toString() {
        return "Workplan{" +
                "initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", nameArea='" + nameArea + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNameArea() {
        return nameArea;
    }

    public String getDni() {
        return dni;
    }


}
