package com.marin.usersms.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateUserRequestModel {

    @NotNull(message = "You must provide a name.")
    @Size(min = 2, message = "First name cannot be shorter than two letters.")
    private String firstName;

    @NotNull(message = "You must provide a last name.")
    @Size(min = 2, message = "Last name cannot be shorter than two letters.")
    private String lastName;

    @NotNull(message = "You must provide a password.")
    @Size(min = 8, max = 16, message = "The password must be between 8 an 16 characters long.")
    private String password;

    @NotNull(message = "You must provide an email.")
    @Email
    private String email;

}
