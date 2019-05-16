package ru.itis.services;

import ru.itis.dto.CompanyDto;
import ru.itis.forms.CompanyCreateForm;
import ru.itis.forms.CompanyEditForm;
import ru.itis.models.Company;

import java.util.Optional;

public interface CompanyService {

    Company createCompany(CompanyCreateForm companyCreateForm, Long userId);

    Optional<Company> getCompanyById(Long companyId);

    CompanyDto showCompany(Long companyId);

    void editCompany(CompanyEditForm companyEditForm);

    void setRequisite( Long requisiteId, Long companyId);

    void deleteCompany(Long companyId);

    void addCompanyAsCarrier(CompanyCreateForm companyCreateForm, Long userId);

    void addCompanyAsCustomer(CompanyCreateForm companyCreateForm, Long userId);


}
