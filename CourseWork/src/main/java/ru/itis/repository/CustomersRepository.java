package ru.itis.repository;

import ru.itis.models.Customer;

import java.util.Optional;

public interface CustomersRepository extends CrudRepository<Customer> {

    Optional<Customer> createCustomer(Customer model);

    Customer findByCompanyID(Long id);

}
