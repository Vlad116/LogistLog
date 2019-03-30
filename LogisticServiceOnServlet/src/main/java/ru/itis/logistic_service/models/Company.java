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


public class Company {

    private Long id;

    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String company_role; // перечисление?

    private User user; //director
    private Customer customer;// choice company role
    private Carrier carrier;
    private Requisite requisite;
    private List<Expense> expenses;

}