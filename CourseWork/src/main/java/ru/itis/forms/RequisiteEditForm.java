package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequisiteEditForm {

    private Long id;
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
