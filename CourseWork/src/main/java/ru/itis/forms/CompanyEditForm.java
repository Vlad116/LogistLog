package ru.itis.forms;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CompanyEditForm {

    private Long id;
    private String companyName;
    private String directorName;
    private String phoneNumber;

}
