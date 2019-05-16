package ru.itis.services;

import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.dto.CarriersDto;
import ru.itis.forms.*;

import java.util.List;

public interface CarrierService {

//    void addDriver(DriverAddForm driverAddForm);
//
//    void editDriver(DriverEditForm driverEditForm);
//
//    void addTractor(TractorAddForm tractorAddForm);
//
//    void editTractor(TractorEditForm tractorEditForm);
//
//    void addTrailer(TrailerAddForm trailerAddForm);
//
//    void editTrailer(TractorEditForm tractorEditForm);

    List<CarriersDto> showCarriers();

    List<ApplicationWithInfoAboutCustomerDto> showAllAplyAplication(Long carrierId);

    void respondApplication (Long carrierId,Long applicationId);
}
