package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.itis.dto.CompanyDto;
import ru.itis.forms.CompanyCreateForm;
import ru.itis.forms.CompanyEditForm;
import ru.itis.models.Carrier;
import ru.itis.models.Company;
import ru.itis.models.Customer;
import ru.itis.repository.CarriersRepository;
import ru.itis.repository.CompaniesRepository;
import ru.itis.repository.CustomersRepository;

import java.util.Optional;

//@Lazy
@Component
public class CompanyServiceImpl implements CompanyService {

    CompaniesRepository companiesRepository;
    CarriersRepository carriersRepository;
    CustomersRepository customersRepository;
    UsersService usersService;

//    @Lazy
    @Autowired
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
                .companyRole(companyCreateForm.getCompanyRole())
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
    public void setRequisite(Long requisiteId,Long companyId) {
        companiesRepository.setResuisiteID(requisiteId,companyId);
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
