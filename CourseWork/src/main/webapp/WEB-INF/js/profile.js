function editUser(email, phoneNumber, login, firstName, lastName, address) {
    $.ajax({
        type: 'post',
        url: '/profile',
        data: {
            email: email,
            phoneNumber: phoneNumber,
            login: login,
            firstName: firstName,
            lastName: lastName,
            address: address
        }
    }).done(function () {
        alert("Профиль успешно отредактирован");
        let contentUserProfileTabDiv = document.getElementById("userProfile");
        contentUserProfileTabDiv.innerHTML = " ";

        let contentUserProfileHTML = "<div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Email:</label>\n" +
            "                                <div class=\"col-lg-10\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"email\" type=\"email\">" + data.email + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Phone number:</label>\n" +
            "                                <div class=\"col-lg-10\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"phoneNumber\"\n" +
            "                                         type=\"text\">" + data.phoneNumber + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-4 col-form-label form-control-label\">Username:</label>\n" +
            "                                <div class=\"col-lg-8\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"login\" type=\"text\">" + data.login + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">First name:</label>\n" +
            "\n" +
            "                                <div class=\"col-lg-4\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"firstName\"\n" +
            "                                         type=\"text\">" + data.firstName + "</div>\n" +
            "                                </div>\n" +
            "\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Last name:</label>\n" +
            "                                <div class=\"col-lg-4\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"lastName\"\n" +
            "                                         type=\"text\">" + data.lastName + "</div>\n" +
            "                                </div>\n" +
            "\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-4 col-form-label form-control-label\">Address:</label>\n" +
            "                                <div class=\"col-lg-8\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"address\" type=\"text\"\n" +
            "                                    \">" + data.address + "</div>\n" +
            "                            </div>\n" +
            "                    </div>\n" +
            " <hr>\n" + "\n" +
            "                        <div class=\"form-group row\">\n" +
            "                            <div class=\"col-lg-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
            "                                        data-target=\"#EditUserModal\">\n" +
            "                                    Edit user\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"col-lg-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
            "                                        data-target=\"#EditUserPasswordModal\">\n" +
            "                                    Edit password\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>";
        contentUserProfileTabDiv.innerHTML = contentUserProfileHTML

        // showUser();
    }).fail(function () {
        alert("При изменении профиля что-то пошло не так");
    })
}

function editPassword(password) {
    $.ajax({
        type: 'post',
        url: '/editpassword',
        data: {
            password: password
        }
    }).done(function () {
        alert("Пароль успешно изменен")
    }).fail(function () {
        alert("При изменении пароля что-то пошло не так");
    })
}

function addCompany(companyName, directorName, phoneNumber, companyRole) {
    $.ajax({
        type: 'post',
        url: '/addcompany',
        data: {
            companyName: companyName,
            directorName: directorName,
            phoneNumber: phoneNumber,
            companyRole: companyRole
        }
    }).done(function (data) {
        // showUser();
        let contentCompanyTabDiv = document.getElementById("companyProfile");
        contentCompanyTabDiv.innerHTML = " ";

        // if (data !== null) {
        //     alert("Пришли данные");
        // }

        let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.company_role + ")</h4>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
            "                                number:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n";
        contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
        alert("Компания" + data.get(companyName) + "отредактирована");
    }).fail(function () {
        alert("При редактировании компании что-то пошло не так");
    });
}

function editCompany(companyName, directorName, phoneNumber) {
    $.ajax({
        type: 'post',
        url: '/editcompany',
        data: {
            companyName: companyName,
            directorName: directorName,
            phoneNumber: phoneNumber
        }
    }).done(function (data) {
        // showCompany();
        // let contentTableHTML = "<table>";
        // contentTableHTML += "<tr>" +
        //     "<th>Номер</th>" +
        //     "<th>Product name</th>\n" +
        //     "<th>Price</th>\n" +
        //     "</tr>";
        // for (let i = 0; i < data.length - 1; i++) {
        //     contentTableHTML += "<tr>";
        //     contentTableHTML += "<td>" + [i] + "</td>";
        //     contentTableHTML += "</tr>";
        // }
        // contentTableHTML += "</table>";
        // let contentTableDiv = document.getElementById("table");
        // contentTableDiv.innerHTML = contentTableHTML;
        let contentCompanyTabDiv = document.getElementById("companyProfile");
        contentCompanyTabDiv.innerHTML = " ";

        // if (data !== null) {
        //     alert("Пришли данные");
        // }

        let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.company_role + ")</h4>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
            "                                number:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n";
        contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
        alert("Компания" + data.get(companyName) + "добавлена");
    }).fail(function () {
        alert("При добавлении компании что-то пошло не так");
    });
}

function addApplication(loadingAddress, unloadingAddress, loadingType, weight, payment) {
    $.ajax({
        type: 'post',
        url: '/addadpplication',
        data: {
            loadingAddress: loadingAddress,
            unloadingAddress: unloadingAddress,
            loadingType: loadingType,
            weight: weight,
            payment: payment
        }
    }).done(function (data) {
        // showAllAddedApplication();
        let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
        contentApplicationTableDiv.innerHTML = " ";

        // if (data !== null) {
        //     alert("Данные пришли")
        // }

        var rowCounterApp = 0;
        let contentAllApplicationTableHTML = "<table\n" +
            "    class=\"table table-striped table-hover\">\n" +
            "\n" +
            "            <thead>\n" +
            "            <tr>\n" +
            "             <th scope = \"col\"> # </th>\n" +
            "             <th scope = \"col\"> Date </th>\n" +
            "             <th scope = \"col\"> Loading address </th>\n" +
            "             <th scope = \"col\"> Unloading address </th>\n" +
            "             <th scope = \"col\"> Loading type </th>\n" +
            "             <th scope = \"col\"> Weight(KG) </th>\n" +
            "             <th scope = \"col\"> Payment(RUB) </th>\n" +
            "             <th scope=\"col\"> </th>\n" +
            "            </tr>\n" +
            "\n" +
            "            </thead>\n" +
            "\n" +
            "            <tbody>\n";

        for (let i = 0; i < data.length; i++) {

            contentAllApplicationTableHTML += "        <tr>\n" +
                "\n" +
                "        <th scope = \"row\">\n" + rowCounterApp++ + "</th>\n" +
                "\n" +
                "        <td>" + data.date + "</td>\n" +
                "        <td>" + data.loadingAddress + "</td>\n" +
                "        <td>" + data.unloadingAddress + "</td>\n" +
                "        <td>" + data.loadingType + "</td>\n" +
                "        <td>" + data.weight + "</td>\n" +
                "        <td>" + data.payment + "</td>\n" +
                "        <td>\n" +
                "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick = \" \" > Delete\n" +
                "            </button>\n" +
                "            </td>\n" +
                "\n" +
                "            </tr>\n" +
                "\n";
        }
        contentAllApplicationTableHTML += "            </tbody>\n" +
            "            </table>";
        contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;
        alert("Заявка добавлена");
    }).fail(function () {
        alert("Что-то пошло не так");
    });
}

function showAllProfileData() {
    // showUser();
    // showCompany();
    // showAllAddedApplication();
}

function showUser() {
    $.ajax({
        type: 'get',
        url: '/profile'
    }).done(function (data) {
        let contentUserProfileTabDiv = document.getElementById("userProfile");
        contentUserProfileTabDiv.innerHTML = " ";

        let contentUserProfileHTML = "<div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Email:</label>\n" +
            "                                <div class=\"col-lg-10\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"email\" type=\"email\">" + data.email + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Phone number:</label>\n" +
            "                                <div class=\"col-lg-10\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"phoneNumber\"\n" +
            "                                         type=\"text\">" + data.phoneNumber + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-4 col-form-label form-control-label\">Username:</label>\n" +
            "                                <div class=\"col-lg-8\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"login\" type=\"text\">" + data.login + "</div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">First name:</label>\n" +
            "\n" +
            "                                <div class=\"col-lg-4\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"firstName\"\n" +
            "                                         type=\"text\">" + data.firstName + "</div>\n" +
            "                                </div>\n" +
            "\n" +
            "                                <label class=\"col-lg-2 col-form-label form-control-label\">Last name:</label>\n" +
            "                                <div class=\"col-lg-4\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"lastName\"\n" +
            "                                         type=\"text\">" + data.lastName + "</div>\n" +
            "                                </div>\n" +
            "\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"form-group row\">\n" +
            "                                <label class=\"col-lg-4 col-form-label form-control-label\">Address:</label>\n" +
            "                                <div class=\"col-lg-8\">\n" +
            "                                    <div class=\"form-control-plaintext\" name=\"address\" type=\"text\"\n" +
            "                                    \">" + data.address + "</div>\n" +
            "                            </div>\n" +
            "                    </div>\n" +
            " <hr>\n" + "\n" +
            "                        <div class=\"form-group row\">\n" +
            "                            <div class=\"col-lg-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
            "                                        data-target=\"#EditUserModal\">\n" +
            "                                    Edit user\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"col-lg-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
            "                                        data-target=\"#EditUserPasswordModal\">\n" +
            "                                    Edit password\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>";

        contentUserProfileTabDiv.innerHTML = contentUserProfileHTML
    })
}

function showCompany() {
    $.ajax({
        type: 'get',
        url: '/profile'
    }).done(function (data) {

        let contentCompanyTabDiv = document.getElementById("companyProfile");
        contentCompanyTabDiv.innerHTML = " ";

        // if (data !== null) {
        //     alert("Пришли данные");
        // }

        let contentCompanyTabHTML = "   <h4 class=\"m-y-2\">Company profile( role - " + data.company_role + ")</h4>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewCompanyName\">Company\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewCompanyName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewCompanyName\">" + data.companyName + "</div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"viewDirectorName\">Director\n" +
            "                                name:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewDirectorName\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewDirectorName\">" + data.directorName + "\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n" +
            "\n" +
            "                        <div class=\"form-group row\">\n" +
            "\n" +
            "                            <label class=\"col-lg-4 col-form-label form-control-label\" for=\"editPhoneNumber\">Phone\n" +
            "                                number:</label>\n" +
            "\n" +
            "                            <div class=\"col-lg-8\">\n" +
            "                                <div type=\"text\" name=\"viewPhoneNumber\" class=\"form-control-plaintext\"\n" +
            "                                     id=\"viewPhoneNumber\">" + data.phoneNumber + "</div>\n" +
            "                            </div>\n" +
            "\n" +
            "                        </div>\n";
        contentCompanyTabDiv.innerHTML = contentCompanyTabHTML;
    }).fail(function () {
        alert("Все плохо!");
    })
}

function showAllAddedApplication() {
    $.ajax({
        type: 'get',
        url: '/profile'
    }).done(function (data) {

            let contentApplicationTableDiv = document.getElementById("allAddedApplicationTable");
            contentApplicationTableDiv.innerHTML = " ";

            // if (data !== null) {
            //     alert("Данные пришли")
            // }

            var rowCounterApp = 0;
            let contentAllApplicationTableHTML = "<table\n" +
                "    class=\"table table-striped table-hover\">\n" +
                "\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "             <th scope = \"col\"> # </th>\n" +
                "             <th scope = \"col\"> Date </th>\n" +
                "             <th scope = \"col\"> Loading address </th>\n" +
                "             <th scope = \"col\"> Unloading address </th>\n" +
                "             <th scope = \"col\"> Loading type </th>\n" +
                "             <th scope = \"col\"> Weight(KG) </th>\n" +
                "             <th scope = \"col\"> Payment(RUB) </th>\n" +
                "             <th scope=\"col\"> </th>\n" +
                "            </tr>\n" +
                "\n" +
                "            </thead>\n" +
                "\n" +
                "            <tbody>\n";

            for (let i = 0; i < data.length; i++) {

                contentAllApplicationTableHTML += "        <tr>\n" +
                    "\n" +
                    "        <th scope = \"row\">\n" + rowCounterApp++ + "</th>\n" +
                    "\n" +
                    "        <td>" + data.date + "</td>\n" +
                    "        <td>" + data.loadingAddress + "</td>\n" +
                    "        <td>" + data.unloadingAddress + "</td>\n" +
                    "        <td>" + data.loadingType + "</td>\n" +
                    "        <td>" + data.weight + "</td>\n" +
                    "        <td>" + data.payment + "</td>\n" +
                    "        <td>\n" +
                    "        <button type = \"button\" class=\"btn btn-outline-danger\" onclick = \" \" > Delete\n" +
                    "            </button>\n" +
                    "            </td>\n" +
                    "\n" +
                    "            </tr>\n" +
                    "\n";
            }
            contentAllApplicationTableHTML += "            </tbody>\n" +
                "            </table>";
            contentApplicationTableDiv.innerHTML = contentAllApplicationTableHTML;

        }
    )
}


function sendProfileImg() {
    $.ajax({
        type: 'post',
        url: '/uploadAvatar',
    }).done(function () {
        alert("Аватар загружен")
    }).fail(function () {
        alert("Что то пошло не так")
    });
}

function showProfileImg() {
    $.ajax({
        type: 'get',
        url: '/showAvatar',
    }).done(function (data) {
        alert("Аватар загружен")
        //    выводим изображение
    }).fail(function () {
        alert("Что то пошло не так")
    });
}

function exit() {
    var date = new Date(0);
    document.cookie = "auth=; path=/; expires=" + date.toUTCString();
    document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
    window.location.replace("/home")
}