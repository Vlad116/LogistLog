package ru.itis.services;

import ru.itis.dto.ApplicationWithInfoAboutCarrierDto;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.forms.ApplicationAddForm;

import java.util.List;

public interface CustomerService {

    List<ApplicationWithInfoAboutCustomerDto> showAllAddedApplication(Long customerId);

    void addApplication(ApplicationAddForm applicationAddForm);

//    void editApplication (ApplicationEditForm applicationEditForm);

    List<ApplicationWithInfoAboutCarrierDto> showAllAplyAplication(Long customerId);

    void deleteApplication(Long applicationId);

}
