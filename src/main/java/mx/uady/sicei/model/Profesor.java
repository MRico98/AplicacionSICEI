package mx.uady.sicei.model;

import java.sql.Time;
import java.time.LocalDate;

public class Profesor {
    // POJO: Plain Java Object. No existe ninguna accion
    private Integer id;
    private String nombre;
    private String email;
    private String apellido;
    private LocalDate fechadenacimiento;
    private Time horadeclase;

    public Profesor() {
    }

    public Profesor(Integer id, String nombre,String email,String apellido,LocalDate fechadenacimiento,Time horadeclase) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
        this.fechadenacimiento = fechadenacimiento;
        this.horadeclase = horadeclase;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor id(Integer id) {
        this.id = id;
        return this;
    }

    public Profesor nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profesor email(String email) {
        this.email = email;
        return this;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Profesor apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public LocalDate getFechadenacimiento() {
        return this.fechadenacimiento;
    }

    public void setFechadenacimiento(LocalDate fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public Profesor fechadenacimiento(LocalDate fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
        return this;
    }

    public Time getHoradeclase() {
        return this.horadeclase;
    }

    public void setHoradeclase(Time horadeclase) {
        this.horadeclase = horadeclase;
    }

    public Profesor horadeclase(Time horadeclase) {
        this.horadeclase = horadeclase;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
