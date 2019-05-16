package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {

    private String name;
    private String email;
    private String password;
    private String message;

}
