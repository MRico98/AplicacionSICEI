package mx.uady.sicei.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class TutoriaRequest {
    
        @NotNull(message = "Ingrese un id de profesor")
        private int profesor;

        @NotNull(message = "Ingrese un id de alumno")
        private int alumno;

        @NotNull(message = "Ingrese las horas de la tutoria")
        @Min(value = 1, message = "Las horas tiene que se mayor o igual a 1")
        @Max(value = 2, message = "Las horas máximas de tutoría son 2")
        private int horas;
    
        public TutoriaRequest() {
        }
    
        public TutoriaRequest(int profesor, int alumno, int horas) {
            this.profesor = profesor;
            this.alumno = alumno;
            this.horas = horas;
        }

        public int getProfesor() {
            return this.profesor;
        }
    
        public void setProfesor(int id) {
            this.profesor = id;
        }

        public TutoriaRequest profesor(int id) {
            this.profesor = id;
            return this;
        }

        public int getAlumno() {
            return this.alumno;
        }
    
        public void setAlumno(int id) {
            this.alumno = id;
        }

        public TutoriaRequest alumno(int id) {
            this.alumno = id;
            return this;
        }
    
        public int getHoras() {
            return this.horas;
        }
    
        public void setHoras(int horas) {
            this.horas = horas;
        }
    
        public TutoriaRequest horas(int horas) {
            this.horas = horas;
            return this;
        }
}