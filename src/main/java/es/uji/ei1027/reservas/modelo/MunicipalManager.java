package es.uji.ei1027.reservas.modelo;

import java.util.Date;

public class MunicipalManager {
    public MunicipalManager() {
    }
    private String dni;
    private String name;
    private String user;
    private String password;
    private Date initialDate;
    private Integer code;

    @Override
    public String toString() {
        return "MunicipalManager{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", initialDate=" + initialDate +
                ", code=" + code +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }



    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Date getInitialDate() {
        return initialDate;

    }


}




