package mx.uady.sicei.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfesorRequest {
    
        @NotEmpty(message = "Ingrese un nombre")
        @Size(min=1,max=255)
        private String nombre;

        @NotNull(message = "Ingrese las horas de clase del maestro")
        @Min(value = 1, message = "Las horas tiene que se mayor o igual a 1")
        private int horas;
    
        public ProfesorRequest() {
        }
    
        public ProfesorRequest(String nombre, int horas) {
            this.nombre = nombre;
            this.horas = horas;
        }

        public String getNombre() {
            return this.nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    
        public ProfesorRequest nombre(String nombre) {
            this.nombre = nombre;
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