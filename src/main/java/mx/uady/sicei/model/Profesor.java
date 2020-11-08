package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profesores")
public class Profesor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column(name = "horas")
    private int horas;

    public Profesor() {
    }

    public Profesor(String nombre,int horas) {
        this.nombre = nombre;
        this.horas = horas;
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

    public int getHoras() {
        return this.horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Profesor horas(int horas) {
        this.horas = horas;
        return this;
    }

}
