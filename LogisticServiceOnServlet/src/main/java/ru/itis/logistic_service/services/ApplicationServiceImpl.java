package ru.itis.logistic_service.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.repositories.ApplicationsRepository;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    ApplicationsRepository applicationsRepository;

    public ApplicationServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompanyInfo() {
        return ApplicationWithInfoAboutCustomerDto.from(applicationsRepository.showAllApplicationWithCompaniesInfo());
    }

    @Override
    public List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress) {
        String from =  loadingAddress + "%";
        String to = unloadingAddress + "%";
        return ApplicationWithInfoAboutCustomerDto.from(applicationsRepository.findByLoadingAndUnloadingAddress(from, to));
    }


}
