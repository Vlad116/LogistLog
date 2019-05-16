package ru.itis.forms;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateForm {

    private String companyName;
    private String directorName;
    private String phoneNumber;
    private String companyRole;
    // если в форме передадим роль customer и она уже не добавлена то:
    // addCustomer(company_id или же обькт компании)
    // company.addCustomerId(customer.getId)
    // так же с carrier
}
