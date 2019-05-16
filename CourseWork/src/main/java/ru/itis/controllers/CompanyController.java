package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CompanyDto;
import ru.itis.forms.CompanyCreateForm;
import ru.itis.forms.CompanyEditForm;
import ru.itis.models.User;
import ru.itis.services.CompanyService;
import ru.itis.services.UsersService;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UsersService userService;

    @PostMapping(value = "/addcompany")
    @ResponseBody
    public CompanyDto addCompany(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("companyName") String companyName, @RequestParam("directorName") String directorName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("companyRole") String companyRole) {

        CompanyCreateForm companyCreateForm = CompanyCreateForm.builder().
                companyName(companyName).
                companyRole(companyRole).
                directorName(directorName).
                phoneNumber(phoneNumber).
                build();

        User profileUser;
        Long userId;
        CompanyDto company = null;

        if (userService.isExistByCookie(authCookie)) {
            profileUser = userService.getUserByUUID(authCookie);
            userId = profileUser.getId();
            Long companyId = userService.getCompanyID(userId);

            if (companyId == null || companyId == 0) {

                if (companyCreateForm.getCompanyRole().equals("Carrier")) {
                    companyService.addCompanyAsCarrier(companyCreateForm, userId);
                    company = companyService.showCompany(companyId);
                    return company;
                } else if (companyCreateForm.getCompanyRole().equals("Customer")) {
                    companyService.addCompanyAsCustomer(companyCreateForm, userId);
                    company = companyService.showCompany(companyId);
                    return company;
                } else {
                    companyService.createCompany(companyCreateForm, userId);
                    company = companyService.showCompany(companyId);
                    return company;
                }

            } else {
                System.out.println("Пользователь с " + userId + " уже завел компанию");
            }
        }

        return company;
    }

    @PostMapping("/editcompany")
    @ResponseBody
    public CompanyDto editCompany(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("companyName") String companyName, @RequestParam("directorName") String directorName, @RequestParam("phoneNumber") String phoneNumber) {

        User profileUser = null;

        if (authCookie != null) {
            if (userService.isExistByCookie(authCookie)) {
                profileUser = userService.getUserByUUID(authCookie);
            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }

        assert profileUser != null;
        Long companyId = userService.getCompanyID(profileUser.getId());

        CompanyEditForm companyEditForm = CompanyEditForm.builder()
                .id(companyId)
                .companyName(companyName)
                .directorName(directorName)
                .phoneNumber(phoneNumber).build();

        companyService.editCompany(companyEditForm);

        return companyService.showCompany(companyId);
    }


}