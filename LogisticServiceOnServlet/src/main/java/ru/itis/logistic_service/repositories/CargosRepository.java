package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Cargo;

import java.util.List;

public interface CargosRepository extends CrudRepository<Cargo> {

    List<Cargo> findAllByType(String type);

}
