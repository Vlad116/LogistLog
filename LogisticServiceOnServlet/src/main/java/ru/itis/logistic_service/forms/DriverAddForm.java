package ru.itis.logistic_service.forms;

import lombok.*;
import ru.itis.logistic_service.models.Carrier;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DriverAddForm {

    private String name;
    private String surname;
    private String phoneNumber;
    private Short age;
    private Short drivingExperience;
    private Carrier carrierCompany;

}
