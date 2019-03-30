<!DOCTYPE html>
<html lang="en">

<head>

    <style>

        @import url('https://fonts.googleapis.com/css?family=Comfortaa:300,400,700|Merriweather&subset=cyrillic');

        .body {
            font-family: 'Comfortaa', cursive;
        }

    </style>

    <title>Registration</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script>

        function exit() {
            var date = new Date(0);
            document.cookie = "auth=; path=/; expires=" + date.toUTCString();
            document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
            window.location.replace("/home")
        }

    </script>

</head>

<body class="body" background="/img/fon.jpg" style="background-size: 100%">

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

            <li class="nav-item active">
                <a class="nav-link" href="/signUp">Sign up</a>
            </li>

            <li class="nav-item">
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

<#--<%--<h1>${locale.get("signup.contact.us")}<a href="/signUp?lang=Ru">RU</a>|<a href="/signUp?lang=En">EN</a></h1>--%>-->

<div class="container" style="padding-top: 1%; padding-left: 25%; padding-bottom: 1%">
    <div class="row">

        <div class="col-md-10 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
            <div class="panel panel-default"
                 style="background: ghostwhite; border: solid rgba(55,55,58,0.83) 2px; padding: 3%">

                <div class="panel-heading" style="border-bottom: solid rgba(55,55,58,0.83) 1px; padding-bottom: 1%">
                    <strong> New User ? Register Yourself!</strong>
                </div>

                <div class="panel-body" style="padding-top: 2%">

                    <form role="form" class="needs-validation" action="/signUp" method="post">

                        <div class="form-group col-md-10">

                            <label for="validationTooltipEmail">Email adress</label>

                            <div class="input-group">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="validationTooltipEmailPrepend">@</span>
                                </div>

                                <input type="email" name="email" class="form-control" id="validationTooltipEmail"
                                       placeholder="someemail@example.com"
                                       aria-describedby="validationTooltipEmailPrepend"
                                       required=""
                                       autofocus="">

                                <div class="invalid-tooltip">
                                    Please enter your valid email
                                </div>

                            </div>

                        </div>

                        <div class="form-group col-md-10">

                            <label for="validationTooltipPhoneNumber">Phone number</label>

                            <div class="input-group">

                                <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipPhoneNumberPrepend">
                                            <i class="fa fa-tag"></i> </span>
                                </div>

                                <input type="text" name="phoneNumber" class="form-control"
                                       id="validationTooltipPhoneNumber"
                                       placeholder="phoneNumber"
                                       value=""
                                       required>

                                <div class="valid-tooltip">
                                    Looks good!
                                </div>

                            </div>

                        </div>

                        <div class="form-group row col-md-10">

                            <div class="form-group col-md-5">
                                <label for="validationTooltipUsername">Username</label>
                                <div class="input-group">

                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipUsernamePrepend">
                                            <i class="fa fa-tag"></i></span>
                                    </div>

                                    <input type="text" name="login" class="form-control"
                                           id="validationTooltipUsername"
                                           placeholder="Username" aria-describedby="validationTooltipUsernamePrepend"
                                           required=""
                                           autofocus="">

                                    <div class="invalid-tooltip">
                                        Please choose a unique and valid username.
                                    </div>
                                </div>
                            </div>


                            <div class="form-group col-md-5">
                                <label for="validationTooltipPassword">Password</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipPasswordPrepend"><i
                                                class="fa fa-lock"></i></span>
                                    </div>

                                    <input type="password" name="password" class="form-control"
                                           id="validationTooltipPassword"
                                           placeholder="Enter password"
                                           aria-describedby="validationTooltipUsernamePrepend"
                                           required="">

                                    <div class="invalid-tooltip">
                                        Enter password.
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div class="form-group row col-md-10">

                            <div class="form-group col-md-5">

                                <label for="validationTooltipFirstname">First name</label>
                                <div class="input-group">

                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipFirstnamePrepend">
                                            <i class="fa fa-tag"></i> </span>
                                    </div>


                                    <input type="text" name="firstName" class="form-control"
                                           id="validationTooltipFirstname"
                                           placeholder="First name"
                                           value=""
                                           required>

                                    <div class="valid-tooltip">
                                        Looks good!
                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-md-5">

                                <label for="validationTooltipLastName">Last name</label>

                                <div class="input-group">

                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipLastNamePrepend">
                                            <i class="fa fa-tag"></i> </span>
                                    </div>

                                    <input type="text" name="lastName" class="form-control"
                                           id="validationTooltipLastName"
                                           placeholder="Last name"
                                           value=""
                                           required>

                                    <div class="valid-tooltip">
                                        Looks good!
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="form-group col-md-10">

                            <label for="validationTooltipAddress">Address</label>

                            <div class="input-group">

                                <div class="input-group-prepend">
                                        <span class="input-group-text" id="validationTooltipAddressPrepend">
                                            <i class="fa fa-tag"></i> </span>
                                </div>

                                <input type="text" name="address" class="form-control" id="validationTooltipAddress"
                                       placeholder="address"
                                       value=""
                                       required>

                                <div class="valid-tooltip">
                                    Looks good!
                                </div>

                            </div>

                        </div>


                        <hr/>

                        <div class="form-group row col-md-10">

                            <!--<a href="#">-->
                            <div class="form-group col-md-4">

                                <input type="submit" class="btn btn-primary"
                                       value="
<#--${locale.get("signup.signup")} -->
                                       Sign Up"/>
                            </div>

                            <div class="form-group col-md-6">
                                <p>Already Registered ? <a href="/signIn">Login here</a></p>
                            </div>

                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

    <#--<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME&ndash;&gt;-->
    <#--<!-- JQUERY SCRIPTS &ndash;&gt;-->
    <#--<script src="../js/jquery-1.10.2.js"></script>-->
    <#--<!-- BOOTSTRAP SCRIPTS &ndash;&gt;-->
    <#--<script src="../js/bootstrap.min.js"></script>-->
    <#--<!-- CUSTOM SCRIPTS &ndash;&gt;-->
    <#--<script src="../js/custom.js"></script>-->

    <#--<!-- Bootstrap core JavaScript ================================================== &ndash;&gt;-->
    <script>window.jQuery || document.write('<script src="../js/jquery-1.10.2.js"><\/script>')</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


</body>
</html>
