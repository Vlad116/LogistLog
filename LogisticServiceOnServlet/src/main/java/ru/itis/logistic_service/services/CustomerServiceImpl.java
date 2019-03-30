package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.AddedApplicationDto;
import ru.itis.logistic_service.forms.ApplicationAddForm;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.repositories.ApplicationsRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    ApplicationsRepository applicationsRepository;

    public CustomerServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public List<AddedApplicationDto> showAllAddedApplication(Long customerId) {
        return AddedApplicationDto.from(applicationsRepository.showAllApplicationByCustomerId(customerId));
    }

    @Override
    public void addApplication(ApplicationAddForm applicationAddForm) {
        Application application = Application.builder()
//                .date(applicationAddForm.getDate())
                .loadingAddress(applicationAddForm.getLoadingAddress())
                .unloadingAddress(applicationAddForm.getUnloadingAddress())
                .loadingType(applicationAddForm.getLoadingType())
                .weight(applicationAddForm.getWeight())
                .payment(applicationAddForm.getPayment())
                .customer(applicationAddForm.getCustomer())
                .build();

        System.out.println(application);
        applicationsRepository.save(application);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        applicationsRepository.delete(applicationId);
    }


}
