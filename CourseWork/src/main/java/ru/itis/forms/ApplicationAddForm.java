package ru.itis.forms;

import lombok.*;
import ru.itis.models.Carrier;
import ru.itis.models.Customer;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationAddForm {

//    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType; // перечисление!?
    private Integer weight;
    private Integer payment;

    private Customer customer;
    private Carrier carrier;
//    private List<Cargo> cargos;

}
