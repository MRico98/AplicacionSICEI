package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorias")
public class Profesor {

    @ManyToOne(mappedBy = "tutoria")
    private Integer profesor_id;

    @ManyToOne(mappedBy = "tutoria")
    private Integer alumno_id;

    //@EmbeddedId
    //TutoriaLlave id;

    @Column(name = "horas")
    private int horas;

    public Tutoria() {
    }

    public Tutoria(Integer profesor, Integer alumno, int horas) {
        this.profesor_id = profesor;
        this.alumno_id = alumno;
        this.horas = horas;
    }

    public Integer getProfesor() {
        return this.profesor_id;
    }

    public void setProfesor(Integer id) {
        this.profesor_id = id;
    }

    public Integer getAlumno() {
        return this.alumno_id;
    }

    public void setAlumno(Integer id) {
        this.alumno_id = id;
    }

    public int getHoras() {
        return this.horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
}
