package ru.itis.logistic_service.models;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Customer {
    private Long id;

    private Company company;
    private List<Application> actualApplications;
}