package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @JoinColumn(name="id_alumno", nullable=false, insertable = false, updatable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name="id_profesor", nullable=false, insertable = false, updatable = false)
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
