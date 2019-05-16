package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Company;
import ru.itis.models.User;

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
