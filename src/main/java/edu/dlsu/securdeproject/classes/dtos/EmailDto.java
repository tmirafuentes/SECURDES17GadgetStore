package edu.dlsu.securdeproject.classes.dtos;

import edu.dlsu.securdeproject.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class EmailDto {
    @ValidEmail
    @NotEmpty
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
