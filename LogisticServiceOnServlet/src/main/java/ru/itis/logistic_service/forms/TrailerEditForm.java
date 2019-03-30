package ru.itis.logistic_service.forms;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrailerEditForm {

    private Long id;
    private String registrationNumber;
    private Byte yearsOfExploitation;
    private Integer mileage;

}
