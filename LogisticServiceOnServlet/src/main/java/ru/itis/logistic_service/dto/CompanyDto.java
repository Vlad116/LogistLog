package ru.itis.logistic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;
import ru.itis.logistic_service.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String company_role;
    private User user;
    private Customer customer;// choice company role
    private Carrier carrier;

    public static CompanyDto from(Company company) {
        return CompanyDto.builder()
                .companyName(company.getCompanyName())
                .directorName(company.getDirectorName())
                .phoneNumber(company.getPhoneNumber())
                .company_role(company.getCompany_role())
                .user(company.getUser())
                .carrier(company.getCarrier())
                .customer(company.getCustomer())
                .build();
    }

}
