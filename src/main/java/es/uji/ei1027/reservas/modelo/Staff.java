package es.uji.ei1027.reservas.modelo;

public class Staff {
    public Staff(){}
    private String dni;
    private String name;

    @Override
    public String toString() {
        return "Staff{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }
}
