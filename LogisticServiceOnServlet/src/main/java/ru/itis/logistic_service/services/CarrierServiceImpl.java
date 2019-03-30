package ru.itis.logistic_service.services;

import ru.itis.logistic_service.dto.CarriersDto;
import ru.itis.logistic_service.forms.*;
import ru.itis.logistic_service.models.*;
import ru.itis.logistic_service.repositories.*;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    CarriersRepository carriersRepository;
    DriversRepository driversRepository;
    TractorsRepository tractorsRepository;
    TrailersRepository trailersRepository;
    ApplicationsRepository applicationsRepository;

    public CarrierServiceImpl(CarriersRepository carriersRepository, DriversRepository driversRepository, TractorsRepository tractorsRepository, TrailersRepository trailersRepository, ApplicationsRepository applicationsRepository) {
        this.carriersRepository = carriersRepository;
        this.driversRepository = driversRepository;
        this.tractorsRepository = tractorsRepository;
        this.trailersRepository = trailersRepository;
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public void addDriver(DriverAddForm driverAddForm) {
        Driver driver = Driver.builder()
                .name(driverAddForm.getName())
                .surname(driverAddForm.getSurname())
                .age(driverAddForm.getAge())
                .phoneNumber(driverAddForm.getPhoneNumber())
                .drivingExperience(driverAddForm.getDrivingExperience())
                .carrierCompany(driverAddForm.getCarrierCompany())
                .build();

        driversRepository.save(driver);

    }

    @Override
    public void editDriver(DriverEditForm driverEditForm) {

        Driver driver = Driver.builder()
                .name(driverEditForm.getName())
                .surname(driverEditForm.getSurname())
                .age(driverEditForm.getAge())
                .phoneNumber(driverEditForm.getPhoneNumber())
                .drivingExperience(driverEditForm.getDrivingExperience())
//                .carrierCompany(Carrier.builder().id(driverEditForm.getCarrierCompany().getId()).build())
                .build();

        driversRepository.update(driver);
    }

    @Override
    public void addTractor(TractorAddForm tractorAddForm) {

        Tractor tractor = Tractor.builder()
                .mark(tractorAddForm.getMark())
                .model(tractorAddForm.getModel())
                .type(tractorAddForm.getType())
                .registrationNumber(tractorAddForm.getRegistrationNumber())
                .mileage(tractorAddForm.getMileage())
                .yearsOfExploitation(tractorAddForm.getYearsOfExploitation())
                .carrier(tractorAddForm.getCarrier())
                .build();
        tractorsRepository.save(tractor);

    }

    @Override
    public void editTractor(TractorEditForm tractorEditForm) {

    }

    @Override
    public void addTrailer(TrailerAddForm trailerAddForm) {

        Trailer trailer = Trailer.builder()
                .mark(trailerAddForm.getMark())
                .type(trailerAddForm.getType())
                .registrationNumber(trailerAddForm.getRegistrationNumber())
                .mileage(trailerAddForm.getMileage())
                .yearsOfExploitation(trailerAddForm.getYearsOfExploitation())
                .volume_in_cubic_meters(trailerAddForm.getVolume_in_cubic_meters())
                .inner_width(trailerAddForm.getInner_width())
                .inner_height(trailerAddForm.getInner_height())
                .inner_length(trailerAddForm.getInner_length())
                .carrier(trailerAddForm.getCarrier())
                .build();
        trailersRepository.save(trailer);

    }

    @Override
    public void editTrailer(TractorEditForm tractorEditForm) {

    }

    @Override
    public List<CarriersDto> showCarriers() {
        return CarriersDto.from(carriersRepository.showAllCarriersCompany());
    }

    @Override
    public List<Application> showAllAplyAplication(Long carrierId) {
        return applicationsRepository.showAllApplicationByCarrierId(carrierId);
    }
}
