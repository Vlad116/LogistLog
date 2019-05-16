package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Entity
//@Table(name = "company")
public class Company {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String companyRole; // перечисление?

//    @OneToOne
    private User user; //director

//    @OneToOne
    private Customer customer;// choice company role

//    @OneToOne
    private Carrier carrier;

//    @OneToOne
    private Requisite requisite;

//    private List<Expense> expenses;

}