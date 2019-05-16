package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserEditForm {

    private Long id;
    private String email;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

}
