package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.logistic_service.models.Application;

import java.util.List;

public interface ApplicationService {

    //инфа, и подробная инфа в модальном окне / + возможность откликнуться на заявку / написать письмо заказчику/ добавить в заинтересовавшее
    List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompanyInfo();

    List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress);
//    void addAplication ();

}
