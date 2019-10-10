package com.marin.usersms.ui.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestModel {

    private String email;
    private String password;

}
