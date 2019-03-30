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


public class Carrier {

    private Long id;

    private Integer theNumberOfTrucks;

    //    private List<Wagon> wagons;

    private Company company;
    private List<Wagon> wagons;
    private List<Tractor> tractors;
    private List<Trailer> trailers;
    private List<Driver> drivers;
    private List<Application> acceptedApplications;

}
