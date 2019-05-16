package ru.itis.repository;

import ru.itis.dto.ApplicationWithInfoAboutCarrierDto;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.models.Application;

import java.util.List;

public interface ApplicationsRepository extends CrudRepository<Application> {

    List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompaniesInfo();

    List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress);

    public List<ApplicationWithInfoAboutCarrierDto> showAllApplyApplicationByCustomerId(Long customerId);

    List<ApplicationWithInfoAboutCustomerDto> showAllAddedApplicationByCustomerId(Long customerId);

    List<ApplicationWithInfoAboutCustomerDto> showAllApplyApplicationByCarrierId(Long carrierId);

    void setCarrierID(Long carrierID, Long applicationID);

}
