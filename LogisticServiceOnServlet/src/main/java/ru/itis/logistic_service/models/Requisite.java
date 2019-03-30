package ru.itis.logistic_service.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Requisite {

    private Long id;
    private Company company;

    private String fullCompanyName;
    private String legalAddress;
    private String actualAddress;

    private String inn;
    private String ogrn;
    private String kpp;
    private String okpo;
    private String currentBankAccount;
    private String fullNameOfTheBank;
    private String correspondentAccountOfTheBank;
    private String bic;


}
