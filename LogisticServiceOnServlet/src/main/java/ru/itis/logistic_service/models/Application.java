package ru.itis.logistic_service.models;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Application {

    private Long id;

    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType; // перечисление!?

    private Integer weight;
    private Integer payment;

    private Customer customer;
    private Carrier carrier;
    private List<Cargo> cargos;

}
