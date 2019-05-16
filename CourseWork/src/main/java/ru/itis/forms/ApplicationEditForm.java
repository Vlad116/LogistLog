package ru.itis.forms;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationEditForm {

    //    private Date date;
    private Long id;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType; // перечисление!?
    private Integer weight;
    private Integer payment;

//    private List<Cargo> cargos;

}
