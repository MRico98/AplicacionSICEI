package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {
    
    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    private String email;

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
