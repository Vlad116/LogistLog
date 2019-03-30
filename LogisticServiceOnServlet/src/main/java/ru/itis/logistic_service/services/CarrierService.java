package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.CarriersDto;
import ru.itis.logistic_service.forms.*;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.models.Carrier;

import java.util.List;

public interface CarrierService {

    void addDriver(DriverAddForm driverAddForm);

    void editDriver(DriverEditForm driverEditForm);

    void addTractor(TractorAddForm tractorAddForm);

    void editTractor(TractorEditForm tractorEditForm);

    void addTrailer(TrailerAddForm trailerAddForm);

    void editTrailer(TractorEditForm tractorEditForm);

    List<CarriersDto> showCarriers ();

    List<Application> showAllAplyAplication(Long carrierId);

}
