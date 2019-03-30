package ru.itis.logistic_service.repositories;

import ru.itis.logistic_service.models.Expense;

import java.util.List;

public interface ExpensesRepository extends CrudRepository<Expense> {

    List<Expense> findAllByExpenseItem (String expenseItem );

}
