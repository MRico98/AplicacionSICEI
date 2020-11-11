package mx.uady.sicei.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class TutoriaLlave implements Serializable {

    //https://www.baeldung.com/jpa-many-to-many

    //Fijese que son INTEGER
    private Integer profesorId;
    private Integer alumnoId;

    public TutoriaLlave(){

    }

    public TutoriaLlave(Integer profesor, Integer alumno) {
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
