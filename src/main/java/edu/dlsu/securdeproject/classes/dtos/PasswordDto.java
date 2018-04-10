package edu.dlsu.securdeproject.classes.dtos;

import edu.dlsu.securdeproject.validation.PasswordMatches;
import edu.dlsu.securdeproject.validation.ValidPassword;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.beans.Transient;

public class PasswordDto {
    @ValidPassword
    @NotNull
    @NotEmpty
    private String password;
    private String passwordConfirm;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
