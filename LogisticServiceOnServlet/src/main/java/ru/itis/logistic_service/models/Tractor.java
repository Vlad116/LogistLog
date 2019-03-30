package ru.itis.logistic_service.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Tractor {

    private Long id;
    private String mark;// Перечисление!
    private String model;
    private String type; // Перечисление!
    private String registrationNumber;

    private Byte yearsOfExploitation;
    private Integer mileage;

    private Driver driver;
    private Carrier carrier;
    private List<Wagon> wagons;


}
