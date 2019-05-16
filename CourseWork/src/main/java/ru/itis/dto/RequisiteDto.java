package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Company;
import ru.itis.models.Requisite;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequisiteDto {

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

    public static RequisiteDto from(Optional<Requisite> requisite) {
        return RequisiteDto.builder()
                .company(requisite.get().getCompany())
                .fullCompanyName(requisite.get().getFullCompanyName())
                .legalAddress(requisite.get().getLegalAddress())
                .actualAddress(requisite.get().getActualAddress())
                .inn(requisite.get().getInn())
                .ogrn(requisite.get().getOgrn())
                .kpp(requisite.get().getKpp())
                .okpo(requisite.get().getOkpo())
                .currentBankAccount(requisite.get().getCurrentBankAccount())
                .fullNameOfTheBank(requisite.get().getFullNameOfTheBank())
                .correspondentAccountOfTheBank(requisite.get().getCorrespondentAccountOfTheBank())
                .bic(requisite.get().getBic())
                .build();
    }

}
