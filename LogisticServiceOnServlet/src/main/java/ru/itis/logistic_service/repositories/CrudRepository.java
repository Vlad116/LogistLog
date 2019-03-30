package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> findOneByID(Long id);

    void save(T model);

    void update(T model);

    void delete(Long id);

    List<T> findAll();
}