package mx.uady.sicei.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TutoriaLlave implements Serializable {

    private Integer id_profesor;
    private Integer id_alumno;

    public TutoriaLlave(){

    }

    public TutoriaLlave(Integer profesor, Integer alumno) {
        this.id_profesor = profesor;
        this.id_alumno = alumno;
    }

    public Integer getProfesor() {
        return this.id_profesor;
    }

    public void setProfesor(Integer id) {
        this.id_profesor = id;
    }

    public Integer getAlumno() {
        return this.id_alumno;
    }

    public void setAlumno(Integer id) {
        this.id_alumno = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TutoriaLlave)) return false;
        TutoriaLlave that = (TutoriaLlave) o;
        return Objects.equals(getProfesor(), that.getProfesor()) &&
                Objects.equals(getAlumno(), that.getAlumno());
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(getProfesor(), getAlumno());
    }
}
