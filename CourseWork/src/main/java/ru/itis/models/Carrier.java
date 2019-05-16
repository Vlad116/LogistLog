package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Entity
//@Table(name = "carrier_company")
public class Carrier {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer theNumberOfTrucks;

    //    private List<Wagon> wagons;

//    @OneToOne
    private Company company;
//    private List<Wagon> wagons;
//    private List<Tractor> tractors;
//    private List<Trailer> trailers;
//    private List<Driver> drivers;
//    @OneToMany
    private List<Application> acceptedApplications;

}
