package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Customer;

import java.util.Optional;

public interface CustomersRepository extends CrudRepository<Customer> {

    Optional<Customer> createCustomer(Customer model);

    Customer findByCompanyID (Long id);

}
