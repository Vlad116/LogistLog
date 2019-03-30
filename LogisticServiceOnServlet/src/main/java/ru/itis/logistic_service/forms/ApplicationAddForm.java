package ru.itis.logistic_service.forms;

import ru.itis.logistic_service.models.Cargo;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Customer;

import java.sql.Date;
import java.util.List;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationAddForm {

//    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType; // перечисление!?
    private Integer weight;
    private Integer payment;

    private Customer customer;
    private Carrier carrier;
//    private List<Cargo> cargos;

}
