package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.forms.ApplicationAddForm;
import ru.itis.forms.ApplicationEditForm;
import ru.itis.models.Application;
import ru.itis.repository.ApplicationsRepository;

import java.util.List;

@Component
public class ApplicationServiceImpl implements ApplicationService {

    ApplicationsRepository applicationsRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompanyInfo() {
        return ApplicationWithInfoAboutCustomerDto.from(applicationsRepository.showAllApplicationWithCompaniesInfo());
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress) {
        String from = loadingAddress + "%";
        String to = unloadingAddress + "%";
        return ApplicationWithInfoAboutCustomerDto.from(applicationsRepository.findByLoadingAndUnloadingAddress(from, to));
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

//        System.out.println(application);
        applicationsRepository.save(application);
    }

    @Override
    public void editApplication(ApplicationEditForm applicationEditForm) {
        Application application = Application.builder()
//                .date(applicationAddForm.getDate())
                .id(applicationEditForm.getId())
                .loadingAddress(applicationEditForm.getLoadingAddress())
                .unloadingAddress(applicationEditForm.getUnloadingAddress())
                .loadingType(applicationEditForm.getLoadingType())
                .weight(applicationEditForm.getWeight())
                .payment(applicationEditForm.getPayment())
                .build();

        applicationsRepository.update(application);
    }


}
