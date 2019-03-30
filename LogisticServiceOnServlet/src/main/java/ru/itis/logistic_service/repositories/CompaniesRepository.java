package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Company;

import java.util.Optional;

public interface CompaniesRepository extends CrudRepository<Company> {

    void setCarrierID (Long carrierID, Long companyID);

    void setCustomerID (Long customerID, Long companyID);

    void setUserID (Long UserID, Long companyID);

    Optional <Company> createCompany(Company model);

    Optional<Company> findOneByPhoneNumber(String phoneNumber);

    Optional<Company> findOneByCompanyName(String companyName);

    Optional<Company> findOneByDirectorName(String directorName);

}
