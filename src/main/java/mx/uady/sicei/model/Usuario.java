package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "email")
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column(name = "secret")
    @JsonIgnore
    private String secret;

    public Usuario() {
    }

    public Usuario(Integer id, String usuario, String email) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario id(Integer id) {
        this.id = id;
        return this;
    }

    public Usuario usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    
}