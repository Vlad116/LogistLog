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


public class Wagon {

    private Long id;

    private Driver driver;
    private Carrier carrier;
    private Tractor tractor;
    private Trailer trailer;
}