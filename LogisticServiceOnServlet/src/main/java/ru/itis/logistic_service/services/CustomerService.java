package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.AddedApplicationDto;
import ru.itis.logistic_service.forms.ApplicationAddForm;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.models.Customer;

import java.util.List;

public interface CustomerService {

    List<AddedApplicationDto> showAllAddedApplication(Long customerId);

    void addApplication(ApplicationAddForm applicationAddForm);

//    void editApplication (ApplicationEditForm applicationEditForm);

    void deleteApplication(Long applicationId);

}
