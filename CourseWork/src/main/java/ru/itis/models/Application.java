package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

//@Entity
//@Table(name = "application")
public class Application {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType; // перечисление!?

    private Integer weight;
    private Integer payment;

//    @ManyToOne
    private Customer customer;
//    @ManyToOne
    private Carrier carrier;
//    private List<Cargo> cargos;

}
