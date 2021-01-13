package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.uady.sicei.model.Licenciatura;

public class UsuarioRequest {

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    private String usuario;

    @NotNull
    @Size(min = 5, max = 25)
    @NotEmpty
    private String email;

    @NotNull
    @Size(min = 5, max = 25)
    @NotEmpty
    private String nombre;

    @NotEmpty
    @NotNull
    private String matricula;

    @NotNull
    @Size(min = 8, max = 50)
    @NotEmpty
    private String password;

    @NotNull
    private Licenciatura licenciatura;

    public UsuarioRequest() {

    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    }

    public Licenciatura getLicenciatura() {
        return licenciatura;
    }
}