package ru.itis.services;

import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.forms.ApplicationAddForm;
import ru.itis.forms.ApplicationEditForm;

import java.util.List;

public interface ApplicationService {

    //инфа, и подробная инфа в модальном окне / + возможность откликнуться на заявку / написать письмо заказчику/ добавить в заинтересовавшее
    List<ApplicationWithInfoAboutCustomerDto> showAllApplicationWithCompanyInfo();

    List<ApplicationWithInfoAboutCustomerDto> findByLoadingAndUnloadingAddress(String loadingAddress, String unloadingAddress);

    void addApplication(ApplicationAddForm applicationAddForm);

    void editApplication(ApplicationEditForm applicationEditForm);

}
