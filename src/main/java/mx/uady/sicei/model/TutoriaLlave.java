package mx.uady.sicei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class TutoriaLlave implements Serializable {

    //https://www.baeldung.com/jpa-many-to-many

    @ManyToOne(mappedBy = "tutoria")
    private Integer profesorId;

    @ManyToOne(mappedBy = "tutoria")
    private Integer alumnoId;

    public Tutoria(Integer profesor, Integer alumno) {
        this.profesorId = profesor;
        this.alumnoId = alumno;
    }

    public Integer getProfesor() {
        return this.profesorId;
    }

    public void setProfesor(Integer id) {
        this.profesorId = id;
    }

    public Integer getAlumno() {
        return this.alumnoId;
    }

    public void setAlumno(Integer id) {
        this.alumnoId = id;
    }
    
}