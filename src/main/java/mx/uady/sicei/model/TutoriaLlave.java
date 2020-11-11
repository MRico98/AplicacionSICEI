package mx.uady.sicei.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TutoriaLlave implements Serializable {

    @Column(name = "id_profesor")
    private Integer profesor;
    @Column(name = "id_alumno")
    private Integer alumno;

    public TutoriaLlave(){

    }

    public TutoriaLlave(Integer profesor, Integer alumno) {
        this.profesor = profesor;
        this.alumno = alumno;
    }

    public Integer getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Integer id) {
        this.profesor = id;
    }

    public Integer getAlumno() {
        return this.alumno;
    }

    public void setAlumno(Integer id) {
        this.alumno = id;
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
