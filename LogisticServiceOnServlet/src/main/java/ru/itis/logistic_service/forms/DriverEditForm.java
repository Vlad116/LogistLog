package ru.itis.logistic_service.forms;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DriverEditForm {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Short age;
    private Short drivingExperience;

}
