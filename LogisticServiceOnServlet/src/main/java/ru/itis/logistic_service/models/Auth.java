package ru.itis.logistic_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Auth {

    private Long id;
    private String cookieValue;
    private Long userId;

}
