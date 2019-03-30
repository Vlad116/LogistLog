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


public class Driver {

    private Long id;

    private String name;
    private String surname;
    private String phoneNumber;
    private Short age;

    private Short drivingExperience;
    private Carrier carrierCompany;
    private List<Wagon> wagons;
    private List<Tractor> tractors;


}
