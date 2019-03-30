package ru.itis.logistic_service.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Expense {

    private Long id;

    private String expenseItem;
    private Integer spent;
    private LocalDateTime date;
    private Company company;

}
