package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.CompanyDto;
import ru.itis.logistic_service.dto.UserDto;
import ru.itis.logistic_service.forms.CompanyCreateForm;
import ru.itis.logistic_service.forms.CompanyEditForm;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;
import ru.itis.logistic_service.repositories.CarriersRepository;
import ru.itis.logistic_service.repositories.CompaniesRepository;
import ru.itis.logistic_service.repositories.CustomersRepository;

import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    CompaniesRepository companiesRepository;
    CarriersRepository carriersRepository;
    CustomersRepository customersRepository;
    UsersService usersService;

    public CompanyServiceImpl(CompaniesRepository companiesRepository, CarriersRepository carriersRepository, CustomersRepository customersRepository, UsersService usersService) {
        this.companiesRepository = companiesRepository;
        this.carriersRepository = carriersRepository;
        this.customersRepository = customersRepository;
        this.usersService = usersService;
    }

    @Override
    public Company createCompany(CompanyCreateForm companyCreateForm, Long userId) {
        Company company = Company.builder()
                .companyName(companyCreateForm.getCompanyName())
                .directorName(companyCreateForm.getDirectorName())
                .phoneNumber(companyCreateForm.getPhoneNumber())
                .company_role(companyCreateForm.getCompany_role())
                .build();
        companiesRepository.save(company);
        companiesRepository.setUserID(userId, company.getId());
        System.out.println(company.getId());
//        companiesRepository.findOneByCompanyName(companyCreateForm.getCompanyName());
        usersService.setCompany(companiesRepository.findOneByCompanyName(companyCreateForm.getCompanyName()).get().getId(), userId);
        return company;
    }

    @Override
    public Optional<Company> getCompanyById(Long companyId) {
        return companiesRepository.findOneByID(companyId);
    }

    @Override
    public CompanyDto showCompany(Long companyId) {
        return CompanyDto.from(companiesRepository.findOneByID(companyId).get());
    }

    @Override
    public void editCompany(CompanyEditForm companyEditForm) {

        Company company = Company.builder()
                .id(companyEditForm.getId())
                .companyName(companyEditForm.getCompanyName())
                .directorName(companyEditForm.getDirectorName())
                .phoneNumber(companyEditForm.getPhoneNumber())
                .build();

        companiesRepository.update(company);

    }

    @Override
    public void deleteCompany(Long companyId) {
        companiesRepository.delete(companyId);
    }

    @Override
    public void addCompanyAsCarrier(CompanyCreateForm companyCreateForm, Long userId) {
        Company newCompany = createCompany(companyCreateForm, userId);
        carriersRepository.createCarrier(Carrier.builder().company(newCompany).build());
        Optional<Carrier> carrier = Optional.ofNullable(carriersRepository.findByCompanyID(newCompany.getId()));
        companiesRepository.setCarrierID(carrier.get().getId(), carrier.get().getCompany().getId());

    }

    @Override
    public void addCompanyAsCustomer(CompanyCreateForm companyCreateForm, Long userId) {

        Company newCompany = createCompany(companyCreateForm, userId);
        customersRepository.createCustomer(Customer.builder().company(newCompany).build());
        Optional<Customer> customer = Optional.ofNullable(customersRepository.findByCompanyID(newCompany.getId()));
        companiesRepository.setCustomerID(customer.get().getId(), customer.get().getCompany().getId());

    }

}
