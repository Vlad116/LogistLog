package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AddedApplicationDto;
import ru.itis.dto.ApplicationWithInfoAboutCustomerDto;
import ru.itis.forms.ApplicationAddForm;
import ru.itis.forms.ApplicationEditForm;
import ru.itis.models.*;
import ru.itis.services.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UsersService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/findApplicationByLocation")
    @ResponseBody
    public List<ApplicationWithInfoAboutCustomerDto> findApplicationByLocation(@RequestBody @RequestParam("loadingAddress") String loadingAdress, @RequestParam("unloadingAddress") String unloadingAdress) {
        return applicationService.findByLoadingAndUnloadingAddress(loadingAdress, unloadingAdress);
    }

    @PostMapping(value = "/addapplication")
    @ResponseBody
    public List<ApplicationWithInfoAboutCustomerDto> addApplication(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("loadingAddress") String loadingAddress, @RequestParam("unloadingAddress") String unloadingAddress, @RequestParam("loadingType") String loadingType, @RequestParam("weight") Integer weight, @RequestParam("payment") Integer payment) {

        List<ApplicationWithInfoAboutCustomerDto> applications = null;

        User profileUser = null;
        if (authCookie != null) {

            if (userService.isExistByCookie(authCookie)) {

                profileUser = userService.getUserByUUID(authCookie);

            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        Optional<Company> company;
        company = companyService.getCompanyById(companyId);

        if (company.isPresent()) {

            if (company.get().getCompanyRole().equals("Customer")) {

                Customer customer = Customer.builder().id(0L).build();

                if (companyId != null && companyId != 0) {

                    customer = company.get().getCustomer();
                    System.out.println(customer);

                }

                if (customer != null && customer.getId() != 0) {

                    ApplicationAddForm applicationAddForm = ApplicationAddForm.builder()
                            .loadingAddress(loadingAddress)
                            .unloadingAddress(unloadingAddress)
                            .loadingType(loadingType)
                            .weight(weight)
                            .payment(payment)
                            .customer(customer)
                            .build();

                    System.out.println(applicationAddForm);
                    customerService.addApplication(applicationAddForm);
                    applications = customerService.showAllAddedApplication(customer.getId());
                } else {
                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
                }
            }
        }


        return applications;
    }

    @PostMapping(value = "/deleteapplication")
    @ResponseBody
    public List<ApplicationWithInfoAboutCustomerDto> deleteApplication(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("id") Long id) {
        List<ApplicationWithInfoAboutCustomerDto> applications = null;
        User profileUser = null;
        if (authCookie != null) {

            if (userService.isExistByCookie(authCookie)) {

                profileUser = userService.getUserByUUID(authCookie);

            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        Optional<Company> company;
        company = companyService.getCompanyById(companyId);

        if (company.isPresent()) {

            if (company.get().getCompanyRole().equals("Customer")) {

                Customer customer = Customer.builder().id(0L).build();
                if (companyId != null && companyId != 0) {
                    customer = company.get().getCustomer();
                    System.out.println(customer);
                }

                if (customer != null && customer.getId() != 0) {
                    customerService.deleteApplication(id);
                    applications = customerService.showAllAddedApplication(customer.getId());
                } else {
                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
                }
            }
        }

        return applications;
    }

    @PostMapping(value = "/respondapplication")
    @ResponseBody
    public List<ApplicationWithInfoAboutCustomerDto> respondApplication(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("id") Long applicationId) {

        System.out.println(applicationId);


        User profileUser = null;
        if (authCookie != null) {

            if (userService.isExistByCookie(authCookie)) {

                profileUser = userService.getUserByUUID(authCookie);

            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        Optional<Company> company;
        company = companyService.getCompanyById(companyId);

        if (company.isPresent()) {

            if (company.get().getCompanyRole().equals("Carrier")) {

                Carrier carrier = Carrier.builder().id(0L).build();
                if (companyId != null && companyId != 0) {

                    carrier = company.get().getCarrier();
                    System.out.println(carrier);

                }

                if (carrier != null && carrier.getId() != 0) {

                    carrierService.respondApplication(carrier.getId(), applicationId);
                    return applicationService.showAllApplicationWithCompanyInfo();
                } else {
                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
                    return applicationService.showAllApplicationWithCompanyInfo();
                }
            }
        }
        return applicationService.showAllApplicationWithCompanyInfo();
    }

    //    @PostMapping(value = "/editapplication")
//    @ResponseBody
//    public List<AddedApplicationDto> editApplication(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("id") Long id, @RequestParam("loadingAddress") String loadingAddress, @RequestParam("unloadingAddress") String unloadingAddress, @RequestParam("loadingType") String loadingType, @RequestParam("weight") Integer weight, @RequestParam("payment") Integer payment) {
//
//        List<AddedApplicationDto> applications = null;
//
//        User profileUser = null;
//        if (authCookie != null) {
//
//            if (userService.isExistByCookie(authCookie)) {
//
//                profileUser = userService.getUserByUUID(authCookie);
//
//            } else {
//                System.out.println("Если нет куки auth, значит не заходил");
//            }
//        }
//
//        assert profileUser != null;
//        Long companyId = profileUser.getCompany().getId();
//        Optional<Company> company;
//        company = companyService.getCompanyById(companyId);
//
//        if (company.isPresent()) {
//
//            if (company.get().getCompanyRole().equals("Customer")) {
//
//                Customer customer = Customer.builder().id(0L).build();
//                if (companyId != null && companyId != 0) {
//
//                    customer = company.get().getCustomer();
//                    System.out.println(customer);
//
//                }
//
//                if (customer != null && customer.getId() != 0) {
//
//                    ApplicationEditForm applicationEditForm = ApplicationEditForm.builder()
//                            .id(id)
//                            .loadingAddress(loadingAddress)
//                            .unloadingAddress(unloadingAddress)
//                            .loadingType(loadingType)
//                            .weight(weight)
//                            .payment(payment)
//                            .build();
//
//                    System.out.println(applicationEditForm);
//                    applicationService.editApplication(applicationEditForm);
//                    applications = customerService.showAllAddedApplication(customer.getId());
//                } else {
//                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
//                }
//            }
//        }
//
//
//        return applications;
//    }

    //    @GetMapping(value = "/showAllAddedApplications", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<ApplicationWithInfoAboutCustomerDto> showAllAddedApplications(@CookieValue("auth") String authCookie) {
//
//        List<ApplicationWithInfoAboutCustomerDto> applications = null;
//
//        User profileUser = null;
//        if (authCookie != null) {
//
//            if (userService.isExistByCookie(authCookie)) {
//
//                profileUser = userService.getUserByUUID(authCookie);
//
//            } else {
//                System.out.println("Если нет куки auth, значит не заходил");
//            }
//        }
//
//        assert profileUser != null;
//        Long companyId = profileUser.getCompany().getId();
//        Optional<Company> company;
//        company = companyService.getCompanyById(companyId);
//
//        if (company.isPresent()) {
//
//            if (company.get().getCompanyRole().equals("Customer")) {
//
//                Customer customer = Customer.builder().id(0L).build();
//                if (companyId != null && companyId != 0) {
//
//                    customer = company.get().getCustomer();
//                    System.out.println(customer);
//
//                }
//
//                if (customer != null && customer.getId() != 0) {
//
//
//                    applications = applicationService.findByLoadingAndUnloadingAddress(applicationAddForm.getLoadingAddress(), applicationAddForm.getUnloadingAddress());
////                    modelMap.addAttribute("applications", applications);
////                    response.setContentType("application/json");
////                    response.getWriter().write(objectMapper.writeValueAsString(applicationWithInfoAboutCustomerDto));
//                } else {
//                    System.out.println("Вначале нужно создать компанию являющуюся заказчиком");
//                }
//            }
//        }
//
//
//        return applications;
//    }

}
