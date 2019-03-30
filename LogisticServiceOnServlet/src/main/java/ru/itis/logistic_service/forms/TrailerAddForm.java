package ru.itis.logistic_service.forms;

import lombok.*;
import ru.itis.logistic_service.models.Carrier;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrailerAddForm {

    private String mark;// Перечисление!
    private String type; // Перечисление!
    private String registrationNumber;
    private Byte yearsOfExploitation;
    private Integer mileage;
    private Integer tonnage;
    private Integer volume_in_cubic_meters;
    private Integer inner_length;
    private Integer inner_width;
    private Integer inner_height;

    private Carrier carrier;
//    private List<Wagon> wagons;
}
