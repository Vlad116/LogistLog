package ru.itis.logistic_service.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private Long id;

    private String email;
    private String login;
    private String hashPassword;


    private String firstName;
    private String lastName;
    private String address;
    private String imgPath;
    private String phoneNumber;
//    private Date birhdayDate;

    private Company company;

}