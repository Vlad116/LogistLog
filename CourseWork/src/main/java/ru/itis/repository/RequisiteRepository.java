package ru.itis.repository;

import ru.itis.models.Requisite;

import java.util.Optional;

public interface RequisiteRepository extends CrudRepository<Requisite> {

    Optional<Requisite> findOneByFullCompanyName(String fullCompanyName);
    Optional<Requisite> findByCompanyId (Long companyId);

}
