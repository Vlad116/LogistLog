package ru.itis.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> findOneByID(Long id);

    List<T> findAll();

    void save(T model);

    void update(T model);

    void delete(Long id);


}