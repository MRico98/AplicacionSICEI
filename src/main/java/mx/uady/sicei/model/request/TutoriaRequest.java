package mx.uady.sicei.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TutoriaRequest {
    
        @NotNull(message = "Ingrese un id de profesor")
        private int profesor_id;

        @NotNull(message = "Ingrese un id de alumno")
        private int alumno_id;

        @NotNull(message = "Ingrese las horas de la tutoria")
        @Min(value = 1, message = "Las horas tiene que se mayor o igual a 1")
        @Max(value = 20, message = "Las horas máximas de tutoría son 20")
        private int horas;
    
        public TutoriaRequest() {
        }
    
        public TutoriaRequest(int profesor, int alumno, int horas) {
            this.profesor_id = profesor;
            this.alumno_id = alumno;
            this.horas = horas;
        }

        public int getProfesor() {
            return this.profesor_id;
        }
    
        public void setProfesor(int id) {
            this.profesor_id = id;
        }

        public TutoriaRequest profesor(int id) {
            this.profesor_id = id;
            return this;
        }

        public int getAlumno() {
            return this.alumno_id;
        }
    
        public void setAlumno(int id) {
            this.alumno_id = id;
        }

        public TutoriaRequest alumno(int id) {
            this.alumno_id = id;
            return this;
        }
    
        public int getHoras() {
            return this.horas;
        }
    
        public void setHoras(int horas) {
            this.horas = horas;
        }
    
        public ProfesorRequest horas(int horas) {
            this.horas = horas;
            return this;
        }
}