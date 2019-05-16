package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.ApplicationWithInfoAboutCarrierDto;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.forms.ApplicationAddForm;
import ru.itis.models.Application;
import ru.itis.repository.ApplicationsRepository;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {

    ApplicationsRepository applicationsRepository;

    @Autowired
    public CustomerServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllAddedApplication(Long customerId) {
        return ApplicationWithInfoAboutCustomerDto.from(applicationsRepository.showAllAddedApplicationByCustomerId(customerId));
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
    public List<ApplicationWithInfoAboutCarrierDto> showAllAplyAplication(Long customerId) {
        return applicationsRepository.showAllApplyApplicationByCustomerId(customerId);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        applicationsRepository.delete(applicationId);
    }


}
