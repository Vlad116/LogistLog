<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>

        @import url('https://fonts.googleapis.com/css?family=Comfortaa:300,400,700|Merriweather&subset=cyrillic');

        .body {
            font-family: 'Comfortaa', cursive;
        }

    </style>

    <title>Home</title>


    <link rel="stylesheet" href="../css/fakeLoader.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>

    <script type="text/javascript">

        // <%
        // --плавный
        // переход-- % >

        $(document).ready(function () {
            $("#secondSlide").on("click", "a", function (event) {
                //отменяем стандартную обработку нажатия по ссылке
                event.preventDefault();

                //забираем идентификатор бока с атрибута href
                var id = $(this).attr('href'),

                        //узнаем высоту от начала страницы до блока на который ссылается якорь
                        top = $(id).offset().top;

                //анимируем переход на расстояние - top за 1500 мс
                $('body,html').animate({scrollTop: top}, 1500);
            });
        });

        $(document).ready(function () {
            $("#thirdSlide").on("click", "a", function (event) {
                //отменяем стандартную обработку нажатия по ссылке
                event.preventDefault();

                //забираем идентификатор бока с атрибута href
                var id = $(this).attr('href'),

                        //узнаем высоту от начала страницы до блока на который ссылается якорь
                        top = $(id).offset().top;

                //анимируем переход на расстояние - top за 1500 мс
                $('body,html').animate({scrollTop: top}, 1500);
            });
        });

        (function ($) {
            $(function () {

                $('#up').click(function () {
                    $('body,html').animate({scrollTop: 0}, 500);
                    return false;
                })

            })
        })(jQuery)

    </script>

    <script>

        function exit() {
            var date = new Date(0);
            document.cookie = "auth=; path=/; expires=" + date.toUTCString();
            document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
            window.location.replace("/home")
        }
    </script>

</head>

<body class="body">

<div id="fakeloader"></div>

<div id="top"></div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="/home">LogistLog</a>

    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse" id="navbar" style="">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/signIn">Sign in</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/signUp">Sign up</a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="/home">Home<span class="sr-only">(current)</span> </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="/profile" id="dropdown05" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Profile</a>
                <div class="dropdown-menu" aria-labelledby="dropdown05">
                    <a class="dropdown-item" href="/profile">User</a>
                    <a class="dropdown-item" href="/profile#company" data-target="#company"
                       data-toggle="tab">Company</a>
                    <a class="dropdown-item" href="/profile" data-target="#application"
                       data-toggle="tab">Application</a>
                </div>

            </li>

        <#--<li class="nav-item">-->
        <#--<a class="nav-link" href="/shop">Shop<span class="sr-only">(current)</span> </a>-->
        <#--</li>-->

            <a class="nav-link" id="showGreetingMessage" style="color: ghostwhite; padding-top: 2%">
            ${greetingMessage}
            </a>

            <img class="img-fluid" onclick="exit()" src="../img/logoutUser.jpg"
                 style=" padding-left: 1%; width: 32px; height: 50px; object-fit: cover;"
                 alt="Exit">

        </ul>

    <#--<form class="form-inline my-2 my-md-0">-->
    <#--<input class="form-control" type="text" placeholder="Search">-->
    <#--</form>-->
    <#---->

    </div>
</nav>


<div id="homeCarousel" class="carousel slide" data-ride="carousel">

    <ol class="carousel-indicators">
        <li data-target="#homeCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#homeCarousel" data-slide-to="1"></li>
        <li data-target="#homeCarousel" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner">

        <div class="carousel-item active">
            <img name="first-slide" class="img-fluid" style="height:100%; width: 100%" src="../img/fon.jpg"
                 alt="First slide">
            <div class="container">
                <div class="carousel-caption text-left">
                <#--<%--Зарегистрироваться сейчас!--%>-->
                <#--<%--Новый сервис для логистики--%>-->
                    <h1>Sign up now!</h1>
                    <p>New service for logistics...</p>
                    <p><a class="btn btn-lg btn-primary" href="/signUp" role="button">Sign
                        up</a></p>
                </div>
            </div>
        </div>

        <div class="carousel-item" id="secondSlide">
            <img name="second-slide" class="img-fluid" style="height:100%; width: 100%"
                 src="../img/trucks-second-slide1920x1080.jpg"
                 alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                <#--<%--Для перевозчиков...--%>-->
                <#--<%--Выбирай удобные тебе заявки--%>-->
                    <h1>For carriers ...</h1>
                    <p>Choose convenient applications!</p>
                    <p><a class="btn btn-lg btn-primary" href="#applicationsTable" role="button">View applications</a>
                    </p>
                </div>
            </div>
        </div>

        <div class="carousel-item" id="thirdSlide">
            <img name="third-slide" class="img-fluid" style="height:100%; width: 100%" src="../img/slide3.jpg"
                 alt="Third slide">
            <div class="container">
                <div class="carousel-caption text-right">
                <#--<%--Для заказчиков...--%>-->
                <#--<%--Размести заявку и на нее обязательно откликнуться. Либо выбери себе перевозчика из-->
                <#--зарегестрированных и свяжись с ним--%>-->
                    <h1>For customers ...</h1>
                    <p>Place a request and respond to it!
                        Or choose your carrier from the registered and contact him</p>
                    <p><a class="btn btn-lg btn-primary" href="#carriersTable" role="button">View carriers</a></p>

                </div>
            </div>
        </div>
    </div>

    <a class="carousel-control-prev" href="#homeCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>

    <a class="carousel-control-next" href="#homeCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>

</div>

<#--<% int rowCounterApp = 0; %>-->

<div class="col-lg-12" id="applicationsDiv" style="padding-top: 5%">

    <h2>Table with all free applications</h2>

<#--<%--Вы можете найти себе заявку, и откликнуться на нее нажав на кнопку "откликнуться" , либо отправив email на почту компании по кнопке "send email"--%>-->

    <p>You can find yourself an application, and respond to it by clicking on the "respond" button,
        or by sending an email to the company email using the "send email" button. <a href="#homeCarousel">(Back to
            top)</a>
    </p>

<#--<form class="search-application-by-location" action="/findApplicationByLocation" method="post">-->
    <div class="form-group row">

        <div class="col-lg-4">
            <div class="search-location-from">
                <div class="search-location cf">
                    <span class="invalid-field-left ng-hide" data-ng-hide="loadsFilter.isFromToFieldValid()">*</span>
                    <input id="from" type="text" name="from" placeholder="From" autocomplete="off"
                           data-focus-on="fromCleared"
                           class="ng-valid ng-valid-required ng-touched ng-valid-editable ng-pristine"
                    >

                <#--<a class="search-clear-input search-clear-location" data-ng-hide="!loadsFilter.filter.from" data-ng-click="loadsFilter.clearFrom()">-->
                <#--x-->
                <#--</a>-->

                </div>

            </div>
        </div>
        <div class="col-lg-4">

            <div class="search-location-to">
                <div class="search-location cf">
                    <span class="invalid-field-left ng-hide" data-ng-hide="loadsFilter.isFromToFieldValid()">*</span>
                    <input id="to" type="text" name="to" placeholder="To" autocomplete="off" data-focus-on="toCleared"
                           class="ng-valid ng-valid-required ng-valid-editable ng-touched ng-pristine">
                    <!-- ngRepeat: match in matches track by $index -->

                <#--<a class="search-clear-input search-clear-location" data-ng-hide="!loadsFilter.filter.to" data-ng-click="loadsFilter.clearTo()">-->
                <#--x-->
                <#--</a>-->
                </div>
            </div>
        </div>
        <div class="col-lg-4">

            <button type="submit" class="btn btn-primary"
                    style=" border-color: darkslategrey; background-color: lightgreen; color: white"
                    onclick="findApplicationsByAddresses($('#from').val(), $('#to').val())"
                    name="findApp" value="">
                Find
            </button>
        </div>
    </div>
    <hr>
</div>

<#--</form>-->

<div class="table-responsive" id="applicationsTable">
    <table class="table table-striped table-hover">

        <thead>
        <tr>

        <#--<th scope="col">#</th>-->
        <#--<th scope="col">Id</th>-->
            <th scope="col">Date</th>
            <th scope="col">Loading address</th>
            <th scope="col">Unloading address</th>
            <th scope="col">Loading type</th>
            <th scope="col">Weight(KG)</th>
            <th scope="col">Payment(RUB)</th>
            <th scope="col">Customer company</th>
            <th scope="col">Contact name</th>
            <th scope="col">Phone number</th>
            <th scope="col">Email</th>
            <th scope="col"></th>
            <th scope="col"></th>

        </tr>

        </thead>

        <tbody>

            <#list applications as application>

            <tr>

            <#--<th scope="row">-->
            <#--<%-->
            <#--rowCounterApp++;-->
            <#--out.print(rowCounterApp);-->
            <#--%>-->
            <#--</th>-->

                <td>${application.date}</td>
                <td>${application.loadingAddress}</td>
                <td>${application.unloadingAddress}</td>
                <td>${application.loadingType}</td>
                <td>${application.weight}</td>
                <td>${application.payment}</td>
                <td>${application.companyName}</td>
                <td>${application.firstName} ${application.lastName}</td>
                <td>${application.phoneNumber}</td>
                <td>${application.email}</td>

            <#--<%--открывает форму отправки сообщения--%>-->
                <td>
                    <button type="button" class="btn btn-outline-info" data-toggle="modal"
                            data-target="#SendEmailModal">Send email
                    </button>
                </td>

            <#--<%--добавляет в мои зявки ?--%>-->
                <td>
                    <button type="button" class="btn btn-outline-success">Respond</button>
                </td>

            </tr>

            </#list>

        </tbody>
    </table>
</div>


<hr class="featurette-divider">

<#--<% int driverTableRowCounter = 0; %>-->

<div class="col-lg-12" id="carriersTable" style="padding-top: 5%">

    <h2>Table with all carriers company, contact one of them</h2>
<#--<%--Если вы заказчик вы можете посмотреть список достпуных перевозчиков--%>-->
    <p>If you are a customer, you can see the list of available carriers. <a href="#homeCarousel">(Back to top)</a></p>

    <div class="table-responsive">
        <table class="table table-striped table-hover">

            <thead>
            <tr>

            <#--<th scope="col">#</th>-->
                <th scope="col">Company name</th>
                <th scope="col">Contact name</th>
                <th scope="col">Contact surname</th>
                <th scope="col">Phone number</th>
                <th scope="col">Email</th>
                <th scope="col">The number of trucks</th>
                <th></th>
            </tr>

            </thead>

            <tbody>

            <#list carriers as carrier>

            <tr>

            <#--<th scope="row">-->
            <#--<%-->
            <#--driverTableRowCounter++;-->
            <#--out.print(driverTableRowCounter);-->
            <#--%>-->
            <#--</th>-->

                <td>${carrier.companyName}</td>
                <td>${carrier.firstName}</td>
                <td>${carrier.lastName}</td>
                <td>${carrier.phoneNumber}</td>
            <#--id="tdEmail"-->
                <td>${carrier.email}</td>
                <td>${carrier.theNumberOfTrucks}</td>

                <td>
                <#--onclick="setCarriesEmailToAndSubjectValue()"-->
                    <button type="button" class="btn btn-outline-info" data-toggle="modal"
                            data-target="#SendEmailModal">Send email
                    </button>
                </td>

            </tr>

            </#list>

            </tbody>
        </table>
    </div>

</div>


<div class="modal fade bd-example-modal-lg" id="SendEmailModal" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalSendEmailLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">
<#--style="background-color: #2EA7EB"-->
            <div class="modal-header bg-dark" >
                <h5 class="modal-title" id="ModalEditUserTitle">Send e-mail message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form role="form" method="POST" action="/sendEmail">

                <div class="modal-body">

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label" for="to">To:</label>
                        <div class="col-lg-10">
                            <input id="to" name="toEmail" type="text"/><br/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label" for="subject">Subject:</label>
                        <div class="col-lg-10">
                            <input id="subject" name="subject" type="text"/><br/>
                        </div>
                    </div>

                    <label class="col-lg-2 col-form-label form-control-label" for="mailbody">Email body:</label>
                    <div class="col-lg-10">
                        <textarea id="mailbody" name="body" cols="60" rows="15"></textarea><br/>
                    </div>

                    <hr>

                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label form-control-label"></label>
                        <div class="col-lg-10">
                            <input type="reset" class="btn btn-secondary" value="Cancel" data-dismiss="modal"
                                   aria-label="Close">
                            <input type="submit" class="btn btn-primary" value="Send email">
                        </div>
                    </div>

                </div>
            </form>

        </div>
    </div>
</div>

<hr class="featurette-divider">

<footer class="
<#--fixed-bottom -->
container">

    <div id="back-top">
        <p><a href="#top" id="up">Back to top</a></p>
    </div>

</footer>

<!-- Bootstrap core JavaScript ================================================== -->
<script>window.jQuery || document.write('<script src="../js/jquery-1.10.2.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script>
    function findApplicationsByAddresses(from, to) {
        $.ajax({
            type: 'post',
            url: '/findApplicationByLocation',
            data: {
                loadingAddress: from,
                unloadingAddress: to
            }
        }).done(function (data) {
            let contentTableDiv = document.getElementById("applicationsTable");
            contentTableDiv.innerHTML = " ";
            var rowCounter = 0;
            let contentTableHTML = "  <div class=\"table-responsive\">\n" +
                    "        <table class=\"table table-striped table-hover\">\n" +
                    "\n" +
                    "            <thead>\n" +
                    "            <tr>\n" +
                    "\n" +
                    "                <th scope=\"col\">#</th>\n" +
                    "                <th scope=\"col\">Date</th>\n" +
                    "                <th scope=\"col\">Loading address</th>\n" +
                    "                <th scope=\"col\">Unloading address</th>\n" +
                    "                <th scope=\"col\">Loading type</th>\n" +
                    "                <th scope=\"col\">Weight(KG)</th>\n" +
                    "                <th scope=\"col\">Payment(RUB)</th>\n" +
                    "                <th scope=\"col\">Customer company</th>\n" +
                    "                <th scope=\"col\">Contact name</th>\n" +
                    "                <th scope=\"col\">Phone number</th>\n" +
                    "                <th scope=\"col\">Email</th>\n" +
                    "                <th scope=\"col\"></th>\n" +
                    "                <th scope=\"col\"></th>\n" +
                    "\n" +
                    "            </tr>\n" +
                    "\n" +
                    "            </thead>\n" +
                    "\n" +
                    "            <tbody>";

            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "<tr>" +
                        "<th scope=\"row\">";
                rowCounter++;
                contentTableHTML += rowCounter + "</th>";
                contentTableHTML += "<td>" + new Date(data[i].date).toDateString() + " " + new Date(data[i].date).toTimeString() + "</td>";
                contentTableHTML += "<td>" + data[i].loadingAddress + "</td>";
                contentTableHTML += "<td>" + data[i].unloadingAddress + "</td>";
                contentTableHTML += "<td>" + data[i].loadingType + "</td>";
                contentTableHTML += "<td>" + data[i].weight + "</td>";
                contentTableHTML += "<td>" + data[i].payment + "</td>";
                contentTableHTML += "<td>" + data[i].companyName + "</td>";
                contentTableHTML += "<td>" + data[i].firstName + " " + data[i].lastName + "</td>";
                contentTableHTML += "<td>" + data[i].phoneNumber + "</td>";
                contentTableHTML += "<td>" + data[i].email + "</td>";
                contentTableHTML += "<td>\n" + "<button type=\"button\" class=\"btn btn-outline-info\" data-toggle=\"modal\"\n" +
                        "data-target=\"#SendEmailModal\">Send email\n" +
                        " </button>\n" +
                        "            </td>\n" +
                        "\n" +

                        "            <td>\n" +
                        "                <button type=\"button\" class=\"btn btn-outline-success\">Respond</button>\n" +
                        "            </td>\n";
                contentTableHTML += "</tr>";
            }

            contentTableHTML += " </tbody>\n" +
                    "        </table>\n" +
                    "        </div>";

            contentTableDiv.innerHTML = contentTableHTML;
        }).fail(function () {
            alert("Что то пошло не так")
        });
    }

</script>

</body>
</html>