package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.dto.CarriersDto;
import ru.itis.models.Company;
import ru.itis.services.ApplicationService;
import ru.itis.services.CarrierService;
import ru.itis.services.CompanyService;
import ru.itis.services.UsersService;

import java.util.List;
import java.util.Optional;

@Controller
public class HomePageController {

    @Autowired
    private UsersService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = {"/home", ""}, method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap, @CookieValue("auth") String authCookie) {


        String greetingMessage = "Hello, anonymous!";


        if (!authCookie.equals("")) {

            greetingMessage = "Welcome, " + userService.getUserByUUID(authCookie).getLogin();
            modelMap.addAttribute("greetingMessage", greetingMessage);

            if (userService.getUserByUUID(authCookie).getCompany() != null && userService.getUserByUUID(authCookie).getCompany().getId() != 0) {
                Optional<Company> company = companyService.getCompanyById(userService.getUserByUUID(authCookie).getCompany().getId());
                modelMap.addAttribute("company", company);
            }
        }

        // выводим все доступные заявки
        List<ApplicationWithInfoAboutCustomerDto> applications = applicationService.showAllApplicationWithCompanyInfo();
        modelMap.addAttribute("applications", applications);

        // выводим всех Carriers
        List<CarriersDto> carriers = carrierService.showCarriers();
        modelMap.addAttribute("carriers", carriers);

        return "home";
    }

//    @RequestMapping(value = "/getappwithcustomerinfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<ApplicationWithInfoAboutCustomerDto> getApplicationWithInfoAboutCustomerAsJson() {
//
//        List<ApplicationWithInfoAboutCustomerDto> applications = applicationService.showAllApplicationWithCompanyInfo();
//        return applications;
//    }
//
//    @RequestMapping(value = "/getcarriers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<CarriersDto>  getCarriersAsJson() {
//        List<CarriersDto> carriers = carrierService.showCarriers();
//        return carriers;
//    }

}
