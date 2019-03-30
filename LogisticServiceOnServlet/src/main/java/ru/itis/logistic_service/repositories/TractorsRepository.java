package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Tractor;

import java.util.List;
import java.util.Optional;

public interface TractorsRepository extends CrudRepository<Tractor> {

    Optional<Tractor> findOneByRegistrationNumber (String registrationNumber);

//    List<Tractor> findAllByMark (String mark);

}
