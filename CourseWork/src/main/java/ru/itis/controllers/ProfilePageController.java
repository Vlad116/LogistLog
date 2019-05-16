package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.*;
import ru.itis.forms.UserEditForm;
import ru.itis.models.Carrier;
import ru.itis.models.Customer;
import ru.itis.models.User;
import ru.itis.services.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfilePageController {

    @Autowired
    private Environment environment;

    @Autowired
    private UsersService userService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(ModelMap modelMap, @CookieValue("auth") String authCookie) {

        User profileUser = null;


        String greetingMessage = "Hello, anonymous!";

        if (authCookie != null) {
            if (userService.isExistByCookie(authCookie)) {

                profileUser = userService.getUserByUUID(authCookie);

                if (!authCookie.equals("")) {
                    greetingMessage = "Welcome, " + profileUser.getLogin();
                }

            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }

        modelMap.addAttribute("greetingMessage", greetingMessage);
        modelMap.addAttribute("profile", profileUser);

        List<String> loadingTypes = Arrays.asList("top", "back", "with full removable curtains", "not specified", "without gates", "side", "with sides", "tail lift", "with the removal of transverse crossbars", "side with 2 sides ", " ramp ", " with the removal of racks");
//                ("верхняя", "задняя", "с полной растентовкой", "не указан", "без ворот", "боковая", "с бортами", "гидроборт", "со снятием поперечных перекладин", "боковая с 2-х сторон", "аппарели", "со снятием стоек");
        modelMap.addAttribute("loadingTypes", loadingTypes);

        assert profileUser != null;
        Long companyId = profileUser.getCompany().getId();
        CompanyDto company;

        if (companyId != null && companyId != 0) {

            company = companyService.showCompany(companyId);
            boolean companyIsPresent = (company != null);

            if (companyIsPresent) {
                modelMap.addAttribute("company", company);
            } else {
                String messageCompany = "Этот пользователь еще не добавил компанию";
                company = CompanyDto.builder().companyRole("no role")
                        .directorName("...")
                        .phoneNumber("...")
                        .companyName("...")
                        .customer(Customer.builder().build())
                        .carrier(Carrier.builder().build())
                        .build();

                modelMap.addAttribute("company", company);
                modelMap.addAttribute("messageCompany", messageCompany);
                return "profile";
            }

            // передаем добавл. заявки
            String messageCustomer = null;

            if (companyIsPresent) {

                Long customerId = company.getCustomer().getId();

                if (customerId != null && customerId != 0) {

                    // выводим все заявки по Customer
                    List<ApplicationWithInfoAboutCustomerDto> addedApplications = customerService.showAllAddedApplication(customerId);
                    List<ApplicationWithInfoAboutCarrierDto> addedApplyedApplications = customerService.showAllAplyAplication(customerId);
                    System.out.println(addedApplyedApplications);
                    messageCustomer = "Все заявки оставленные компанией";
                    modelMap.addAttribute("customerId", customerId);
                    modelMap.addAttribute("messageCustomer", messageCustomer);
                    modelMap.addAttribute("applyApplications", addedApplyedApplications);
                    modelMap.addAttribute("addedApplications", addedApplications);

                } else {
                    messageCustomer = "Компания не является заказчиком";
                    modelMap.addAttribute("messageCustomer", messageCustomer);
                }

            } else {
                messageCustomer = "Этот пользователь еще не добавил компанию-заказчик, a значит не может совершать действия с заявками";
                modelMap.addAttribute("messageCustomer", messageCustomer);
            }

            // передаем принятые заявки
            String messageCarrier = null;

            if (companyIsPresent) {

                Long carrierId = company.getCarrier().getId();

                if (carrierId != null && carrierId != 0) {

                    // выводим все заявки по Carrier
                    List<ApplicationWithInfoAboutCustomerDto> applyApplications = carrierService.showAllAplyAplication(carrierId);
                    System.out.println(applyApplications);
                    modelMap.addAttribute("applyApplications", applyApplications);

                } else {
                    messageCarrier = "Компания не является перевозчиком";
                    modelMap.addAttribute("messageCarrier", messageCarrier);
                }

            } else {
                messageCarrier = "Этот пользователь еще не добавил компанию-перевозчика, a значит не может принимать заявки";
                modelMap.addAttribute("messageCarrier", messageCarrier);
            }
        }

        return "profile";
    }

    @PostMapping("/edituser")
    @ResponseBody
    public UserDto editUserProfile(@CookieValue("auth") String authCookie, @RequestBody @RequestParam("email") String email, @RequestParam("login") String login, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String address, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
    ) {

        User profileUser = null;

        if (authCookie != null) {
            if (userService.isExistByCookie(authCookie)) {
                profileUser = userService.getUserByUUID(authCookie);
            } else {
                System.out.println("Если нет куки auth, значит не заходил");
            }
        }
        assert profileUser != null;

        UserEditForm userEditForm = UserEditForm.builder()
                .id(profileUser.getId())
                .email(email)
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
        userService.editUser(userEditForm);

        System.out.println(userService.showUserById(profileUser.getId()));
        return userService.showUserById(profileUser.getId());
    }

    //
    @PostMapping(value = "/editpassword")
    public ResponseEntity<?> editUserPassword(@RequestParam("password") String password, @CookieValue("auth") String authCookie) {
        userService.setPassword(password, userService.getUserByUUID(authCookie).getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, @CookieValue("auth") String authCookie) {

        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();
                String rootPath = "C:\\Users\\Влад\\Desktop\\LogistLog\\NodeJs_server\\public\\"; //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "userPhotos");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String randomUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
                System.out.println(randomUUID);
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + randomUUID + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                String pathToDB = " http://localhost/userPhotos/" + randomUUID + name;

                userService.setProfileImage(pathToDB, userService.getUserByUUID(authCookie).getId());
//                http://localhost/userPhotos/photo.jpg

                System.out.println("uploaded: " + uploadedFile.getAbsolutePath());
//                logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                System.out.println("You successfully uploaded file=" + name);
//                return "You successfully uploaded file=" + name;

            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
//                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
//            return "You failed to upload " + name + " because the file was empty.";
        }
        System.out.println(userService.uploadUserPhoto(file));
        return "redirect:/profile";
    }

}



