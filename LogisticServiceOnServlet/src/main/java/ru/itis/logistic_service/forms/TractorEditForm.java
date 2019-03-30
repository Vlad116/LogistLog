package ru.itis.logistic_service.forms;

import lombok.*;
import ru.itis.logistic_service.models.Carrier;
import ru.itis.logistic_service.models.Driver;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TractorEditForm {

    private Long id;
    private String registrationNumber;
    private Byte yearsOfExploitation;
    private Integer mileage;

}
