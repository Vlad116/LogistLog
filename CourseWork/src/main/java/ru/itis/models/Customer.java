package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Entity
//@Table(name = "customer_company")
public class Customer {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
    private Company company;

//    @OneToMany
    private List<Application> actualApplications;
}