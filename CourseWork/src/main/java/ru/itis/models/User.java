package ru.itis.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Entity
//@Table(name = "logistic_service_user")
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @OneToOne
    private Company company;

}