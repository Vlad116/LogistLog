package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Application;
import ru.itis.models.Carrier;
import ru.itis.models.Customer;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationWithInfoAboutCarrierDto {
    private Long id;
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
        private String directorName;
    //    - заменили на фио добавившего юзера
//    ot usera
    private String firstName;
    private String lastName;
    private String email;
    private Carrier carrier;


    public static ApplicationWithInfoAboutCarrierDto from(Application application) {
        return ApplicationWithInfoAboutCarrierDto.builder()
                .id(application.getId())
                .date(application.getDate())
                .loadingAddress(application.getLoadingAddress())
                .unloadingAddress(application.getUnloadingAddress())
                .loadingType(application.getLoadingType())
                .weight(application.getWeight())
                .payment(application.getPayment())
                .companyName(application.getCarrier().getCompany().getCompanyName())
                .phoneNumber(application.getCarrier().getCompany().getPhoneNumber())
                .firstName(application.getCarrier().getCompany().getUser().getFirstName())
                .lastName(application.getCarrier().getCompany().getUser().getLastName())
                .email(application.getCarrier().getCompany().getUser().getEmail())
                .build();
    }

    public static List<ApplicationWithInfoAboutCarrierDto> from(List<Application> applications) {
        return applications.stream().map(ApplicationWithInfoAboutCarrierDto::from).collect(Collectors.toList());
    }

}
