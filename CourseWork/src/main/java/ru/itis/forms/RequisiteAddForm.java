package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Company;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequisiteAddForm {

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
    private Company company;
}
