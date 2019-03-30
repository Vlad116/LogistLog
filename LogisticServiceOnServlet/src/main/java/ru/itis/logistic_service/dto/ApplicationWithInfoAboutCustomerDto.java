package ru.itis.logistic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.logistic_service.models.Application;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.Customer;
import ru.itis.logistic_service.models.User;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationWithInfoAboutCustomerDto {
    //ot elements
    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType;
    private String unloadingType;
    private Integer weight;
    private Integer payment;
    //    ot company
    private String companyName;
    private String phoneNumber;
    //    private String directorName; - заменили на фио добавившего юзера
//    ot usera
    private String firstName;
    private String lastName;
    private String email;
//    private Customer customer;


    public static ApplicationWithInfoAboutCustomerDto from(Application application) {
        return ApplicationWithInfoAboutCustomerDto.builder()
                .date(application.getDate())
                .loadingAddress(application.getLoadingAddress())
                .unloadingAddress(application.getUnloadingAddress())
                .loadingType(application.getLoadingType())
                .weight(application.getWeight())
                .payment(application.getPayment())
                .companyName(application.getCustomer().getCompany().getCompanyName())
                .phoneNumber(application.getCustomer().getCompany().getPhoneNumber())
                .firstName(application.getCustomer().getCompany().getUser().getFirstName())
                .lastName(application.getCustomer().getCompany().getUser().getLastName())
                .email(application.getCustomer().getCompany().getUser().getEmail())
                .build();
    }

    public static List<ApplicationWithInfoAboutCustomerDto> from(List<Application> applications) {
        return applications.stream().map(ApplicationWithInfoAboutCustomerDto::from).collect(Collectors.toList());
    }

}
