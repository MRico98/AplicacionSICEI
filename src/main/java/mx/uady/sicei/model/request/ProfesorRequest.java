package mx.uady.sicei.model.request;

import java.sql.Time;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProfesorRequest {
    
        // POJO: Plain Java Object. No existe ninguna accion
        @NotEmpty(message = "Ingrese un nombre")
        @Size(min=1,max=255)
        private String nombre;

        @NotEmpty(message = "Ingrese un email")
        @Size(min=1,max=50)
        private String email;

        @NotEmpty(message = "Ingrese un apellido")
        @Size(min=1,max=255)
        private String apellido;

        @NotEmpty(message = "Ingrese una fecha de nacimiento")
        @Size(min=1,max=255)
        private LocalDate fechadenacimiento;

        @NotEmpty(message = "Ingrese un horario de clase")
        private Time horadeclase;
    
        public ProfesorRequest() {
        }
    
        public ProfesorRequest(String nombre,String email,String apellido,LocalDate fechadenacimiento,Time horadeclase) {
            this.nombre = nombre;
            this.email = email;
            this.apellido = apellido;
            this.fechadenacimiento = fechadenacimiento;
            this.horadeclase = horadeclase;
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
    
        public String getEmail() {
            return this.email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public ProfesorRequest email(String email) {
            this.email = email;
            return this;
        }
    
        public String getApellido() {
            return this.apellido;
        }
    
        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    
        public ProfesorRequest apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }
    
        public LocalDate getFechadenacimiento() {
            return this.fechadenacimiento;
        }
    
        public void setFechadenacimiento(LocalDate fechadenacimiento) {
            this.fechadenacimiento = fechadenacimiento;
        }
    
        public ProfesorRequest fechadenacimiento(LocalDate fechadenacimiento) {
            this.fechadenacimiento = fechadenacimiento;
            return this;
        }
    
        public Time getHoradeclase() {
            return this.horadeclase;
        }
    
        public void setHoradeclase(Time horadeclase) {
            this.horadeclase = horadeclase;
        }
    
        public ProfesorRequest horadeclase(Time horadeclase) {
            this.horadeclase = horadeclase;
            return this;
        }
    
        @Override
        public String toString() {
            return "";
        }   
}