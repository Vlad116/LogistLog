package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Carrier;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarriersDto {
    String companyName;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Integer theNumberOfTrucks;


    public static CarriersDto from(Carrier carrier) {
        return CarriersDto.builder()
                .companyName(carrier.getCompany().getCompanyName())
                .firstName(carrier.getCompany().getUser().getFirstName())
                .lastName(carrier.getCompany().getUser().getLastName())
                .phoneNumber(carrier.getCompany().getPhoneNumber())
                .email(carrier.getCompany().getUser().getEmail())
                .theNumberOfTrucks(carrier.getTheNumberOfTrucks())
                .build();
    }

    public static List<CarriersDto> from(List<Carrier> carriers) {
        return carriers.stream().map(CarriersDto::from).collect(Collectors.toList());
    }

}
