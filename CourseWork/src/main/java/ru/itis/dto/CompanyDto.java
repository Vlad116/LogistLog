package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Carrier;
import ru.itis.models.Company;
import ru.itis.models.Customer;
import ru.itis.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String companyRole;
    private User user;
    private Customer customer;// choice company role
    private Carrier carrier;

    public static CompanyDto from(Company company) {
        return CompanyDto.builder()
                .companyName(company.getCompanyName())
                .directorName(company.getDirectorName())
                .phoneNumber(company.getPhoneNumber())
                .companyRole(company.getCompanyRole())
                .user(company.getUser())
                .carrier(company.getCarrier())
                .customer(company.getCustomer())
                .build();
    }

}
