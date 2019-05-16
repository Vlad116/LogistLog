<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CUSTOM STYLES-->
    <style>

        @import url('https://fonts.googleapis.com/css?family=Comfortaa:300,400,700|Merriweather&subset=cyrillic');

        .body {
            font-family: 'Comfortaa', cursive;
        }

    </style>
    <link href="../css/custom.css" rel="stylesheet"/>

    <title>SignIn</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">


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
                <a class="nav-link active" href="/signIn">Sign in</a>
            </li>

            <li class="nav-item">
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

            <a class="nav-link" id="showGreetingMessage" style="color: ghostwhite; padding-top: 2%">
            ${greetingMessage}
            </a>

            <img class="img-fluid" onclick="exit()" src="/img/logoutUser.jpg"
                 style=" padding-left: 1%; width: 32px; height: 50px; object-fit: cover;"
                 alt="Exit">

        </ul>

    </div>
</nav>

<div class="container" style="padding-top: 2%; padding-left: 25%">

    <div class="row">

        <div class="col-md-6 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

            <div class="panel panel-default"
                 style="background: ghostwhite; border: solid rgba(55,55,58,0.83) 2px; padding: 6%">

                <div class="panel-heading" style=" padding-top: 20px; border-bottom: solid rgba(55,55,58,0.83) 1px;">

                    <h4><strong> Enter details to login </strong></h4>

                </div>

                <div class="panel-body">

                    <form class="form-signin" role="form" action="/signIn" method="post">

                        <br/>

                        <br/>

                        <div class="form-group">

                            <label for="validationTooltipEmail">Email adress</label>

                            <div class="input-group">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="validationTooltipEmailPrepend">@</span>
                                </div>

                                <input type="email" name="login" class="form-control" id="validationTooltipEmail"
                                       placeholder="someemail@example.com"
                                       aria-describedby="validationTooltipEmailPrepend"
                                       required=""
                                       autofocus="">

                                <div class="invalid-tooltip">
                                    Please enter your valid email
                                </div>
                            </div>

                        </div>

                        <div class="form-group ">

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

                        <div class="form-group">

                            <label class="checkbox">
                                <input type="submit" class="btn btn-primary"
                                       value="
<#--${locale.get("signin.signin")}-->
                                        Login"/>
                            </label>

                            <span class="pull-right"
                                  data-toggle="modal"
                                  data-target="#ResetPasswordModal"
                                  style="padding-left: 15px">
                               <a href="#">Forget password ? </a>
                          </span>

                        </div>
                        <hr/>

                        Not register? <a href="/signUp">Register now! </a>

                    </form>
                </div>

            </div>
        </div>

    </div>
</div>

<div class="modal" id="ResetPasswordModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header" style="background-color:lightsteelblue ">
                <h4 class="modal-title">Write your email for password recovery</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">

                <div class="input-group mb-3">
                    <input type="text"
                           class="form-control" id="Email" placeholder="Email"
                           aria-label="Email"
                           aria-describedby="basic-addon1">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary"
                            style="background-color: lightsteelblue; color: white"
                            data-dismiss="modal"
                            data-toggle="modal"
                            data-target="#ResetCodeModal">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal" id="ResetCodeModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: lightsteelblue">
                <h4 class="modal-title">Enter your recovery code</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">

                <div class="input-group mb-3">
                    <input type="text"
                           class="form-control" id="customRecoveryCode" placeholder="Recovery code"
                           aria-label="Recovery code"
                           aria-describedby="basic-addon1">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary"
                            style="background-color: lightsteelblue; color: black"
                            data-dismiss="modal">
                        Enter
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- Bootstrap core JavaScript ================================================== -->
<script>window.jQuery || document.write('<script src="../js/jquery-1.10.2.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</body>
</html>

