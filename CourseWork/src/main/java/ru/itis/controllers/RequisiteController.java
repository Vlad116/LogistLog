package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.RequisiteDto;
import ru.itis.forms.RequisiteAddForm;
import ru.itis.forms.RequisiteEditForm;
import ru.itis.models.Company;
import ru.itis.models.Requisite;
import ru.itis.models.User;
import ru.itis.services.RequisiteService;
import ru.itis.services.UsersService;

@Controller
public class RequisiteController {

//    @Autowired
//    RequisiteService requisiteService;
//
//    @Autowired
//    UsersService usersService;
//
//    @PostMapping(value = "/addrequisite")
//    @ResponseBody
//    public Requisite addResuisite(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("fullCompanyName") String fullCompanyName, @RequestParam("legalAddress") String legalAddress, @RequestParam("actualAddress") String actualAddress, @RequestParam("inn") String inn,@RequestParam("ogrn") String ogrn,@RequestParam("kpp") String kpp,@RequestParam("okpo") String okpo,@RequestParam("currentBankAccount") String currentBankAccount,@RequestParam("fullNameOfTheBank") String fullNameOfTheBank,@RequestParam("correspondentAccountOfTheBank") String correspondentAccountOfTheBank,@RequestParam("bic") String bic) {
//
//
//        Requisite requisite = null;
//        User profileUser;
//        Long userId;
//
//        if (usersService.isExistByCookie(authCookie)) {
//            profileUser = usersService.getUserByUUID(authCookie);
//            userId = profileUser.getId();
//            Long companyId = usersService.getCompanyID(userId);
//
//            RequisiteAddForm requisiteAddForm = RequisiteAddForm.builder()
//                    .company(Company.builder()
//                            .id(companyId)
//                            .build())
//                    .fullCompanyName(fullCompanyName)
//                    .legalAddress(legalAddress)
//                    .actualAddress(actualAddress)
//                    .inn(inn)
//                    .ogrn(ogrn)
//                    .kpp(kpp)
//                    .okpo(okpo)
//                    .currentBankAccount(currentBankAccount)
//                    .fullNameOfTheBank(fullNameOfTheBank)
//                    .correspondentAccountOfTheBank(correspondentAccountOfTheBank)
//                    .bic(bic)
//                    .build();
//            return requisiteService.addRequisite(requisiteAddForm,companyId);
//        }
//
//        return requisite;
//    }
//
//    @PostMapping("/editrequisite")
//    @ResponseBody
//    public Requisite editResuisite(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("fullCompanyName") String fullCompanyName, @RequestParam("legalAddress") String legalAddress, @RequestParam("actualAddress") String actualAddress, @RequestParam("inn") String inn,@RequestParam("ogrn") String ogrn,@RequestParam("kpp") String kpp,@RequestParam("okpo") String okpo,@RequestParam("currentBankAccount") String currentBankAccount,@RequestParam("fullNameOfTheBank") String fullNameOfTheBank,@RequestParam("correspondentAccountOfTheBank") String correspondentAccountOfTheBank,@RequestParam("bic") String bic) {
//
//        Requisite requisite = null;
//        User profileUser;
//        Long userId;
//
//        if (usersService.isExistByCookie(authCookie)) {
//            profileUser = usersService.getUserByUUID(authCookie);
//            userId = profileUser.getId();
//            Long companyId = usersService.getCompanyID(userId);
//
//            RequisiteEditForm requisiteEditForm = RequisiteEditForm.builder()
//                    .id(requisiteService.getRequisiteByCompanyId(companyId).get().getId())
//                    .fullCompanyName(fullCompanyName)
//                    .legalAddress(legalAddress)
//                    .actualAddress(actualAddress)
//                    .inn(inn)
//                    .ogrn(ogrn)
//                    .kpp(kpp)
//                    .okpo(okpo)
//                    .currentBankAccount(currentBankAccount)
//                    .fullNameOfTheBank(fullNameOfTheBank)
//                    .correspondentAccountOfTheBank(correspondentAccountOfTheBank)
//                    .bic(bic)
//                    .build();
//            return requisiteService.editRequisite(requisiteEditForm);
//        }
//        return requisite;
//    }

}
