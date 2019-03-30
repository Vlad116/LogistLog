package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Requisite;

import java.util.Optional;

public interface RequisiteRepository extends CrudRepository<Requisite> {

    Optional<Requisite> findOneByFullCompanyName(String fullCompanyName);

}
