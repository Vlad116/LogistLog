package ru.itis.logistic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.logistic_service.models.Application;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddedApplicationDto {

    private Date date;
    private String loadingAddress;
    private String unloadingAddress;
    private String loadingType;
    private String unloadingType;
    private Integer weight;
    private Integer payment;

    //    ot company carrier
//    private String companyName;
//    private String phoneNumber;
//    //    private String directorName;
////    ot usera
//    private String firstName;
//    private String lastName;
//    private String email;


    public static AddedApplicationDto from(Application application) {
        return AddedApplicationDto.builder()
                .date(application.getDate())
                .loadingAddress(application.getLoadingAddress())
                .unloadingAddress(application.getUnloadingAddress())
                .loadingType(application.getLoadingType())
                .weight(application.getWeight())
                .payment(application.getPayment())
//                .companyName(application.getCarrier().getCompany().getCompanyName())
//                .phoneNumber(application.getCarrier().getCompany().getPhoneNumber())
//                .firstName(application.getCarrier().getCompany().getUser().getFirstName())
//                .lastName(application.getCarrier().getCompany().getUser().getLastName())
//                .email(application.getCarrier().getCompany().getUser().getEmail())
                .build();
    }

    public static List<AddedApplicationDto> from(List<Application> applications) {
        return applications.stream().map(AddedApplicationDto::from).collect(Collectors.toList());
    }
}
