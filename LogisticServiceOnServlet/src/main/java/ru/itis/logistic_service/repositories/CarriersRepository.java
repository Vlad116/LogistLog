package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Carrier;

import java.util.List;
import java.util.Optional;

public interface CarriersRepository extends CrudRepository<Carrier> {

    Optional<Carrier> createCarrier(Carrier model);

    Carrier findByCompanyID(Long id);

    List<Carrier> showAllCarriersCompany();

    Optional<Carrier> findOneByCompanyName(String companyName);

//    List<Carrier> findAllByNumberOfTrucks(Integer theNumberOfTrucks);

}
