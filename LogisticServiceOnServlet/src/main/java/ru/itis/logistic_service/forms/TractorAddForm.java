package ru.itis.logistic_service.forms;

import lombok.*;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Driver;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TractorAddForm {

    private String mark;// Перечисление!
    private String model;
    private String type; // Перечисление!
    private String registrationNumber;
    private Byte yearsOfExploitation;
    private Integer mileage;
//    private Driver driver;
    private Carrier carrier;
//    private List<Wagon> wagons;

}
