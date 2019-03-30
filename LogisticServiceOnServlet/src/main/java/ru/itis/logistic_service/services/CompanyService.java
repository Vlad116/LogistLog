package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.CompanyDto;
import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.*;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Driver;

import java.util.Optional;

public interface CompanyService {

    Company createCompany(CompanyCreateForm companyCreateForm, Long userId);

    Optional<Company> getCompanyById(Long companyId);

    CompanyDto showCompany(Long companyId);

    void editCompany(CompanyEditForm companyEditForm);

    void deleteCompany(Long companyId);

    void addCompanyAsCarrier(CompanyCreateForm companyCreateForm, Long userId);

    void addCompanyAsCustomer(CompanyCreateForm companyCreateForm, Long userId);


}
