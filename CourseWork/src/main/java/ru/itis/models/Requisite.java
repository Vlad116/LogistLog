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
//@Table(name = "requisite")
public class Requisite {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @OneToOne
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
