package mx.uady.sicei.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "tutorias")
public class Tutoria {

    @EmbeddedId
    private TutoriaLlave id;

    @ManyToOne
    @MapsId("alumno")
    @JoinColumn(name="id_alumno")
    private Alumno alumno;

    @ManyToOne
    @MapsId("profesor")
    @JoinColumn(name="id_profesor")
    private Profesor profesor;

    @Column(name = "horas")
    private Integer horas;

    public Tutoria() {
    }

    public Tutoria(TutoriaLlave id, Integer horas) {
        this.id = id;
        this.horas = horas;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public TutoriaLlave getId() {
        return id;
    }

    public void setId(TutoriaLlave id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tutoria [horas=" + horas + ", id=" + id + "]";
    }
    
}
