package ru.itis.logistic_service.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Cargo {

    private Long id;

    private String cargoName;
    private Integer weight;

    private Integer length;
    private Integer width;
    private Integer height;

    private String type;

    private Application application;

}
