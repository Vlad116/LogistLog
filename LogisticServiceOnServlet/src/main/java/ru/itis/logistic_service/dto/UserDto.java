package ru.itis.logistic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.logistic_service.models.Company;
import ru.itis.logistic_service.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String login;
    private String firstName;
    private String lastName;
    private String address;
    private String imgPath;
    private String phoneNumber;
    private Company company;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .imgPath(user.getImgPath())
                .phoneNumber(user.getPhoneNumber())
                .company(user.getCompany())
                .build();
    }

}
